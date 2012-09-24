/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esiea.qrcode;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.util.Log;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.android.RGBLuminance;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francois
 */
public class QRCodeController implements IQRCodeController {

    private QRcodeModel qrcModel;
    private QRCodeView qrView;
    private static QRCodeController qrController = null;

    /**
     * Class constructor who set an new view and model. Arguments : a view
     */
    private QRCodeController(QRCodeView in) {
        this.qrView = in;
        qrcModel = new QRcodeModel();
    }

    /**
     * Set the variable "data" of the model. Ask to the model to generate the
     * QRCode
     */
    public Bitmap updateQRCode(String data, String Hiddendata) {
        qrcModel.setData(data);
        return qrcModel.generateBitmap();
    }

    /**
     * Get the clear data and the hidden data from the view and update the
     * QRCode and update the display
     */
    public void generateAndDisplayQRcode() {
        //Get the clear data and the hidden data from the view
        String data = qrView.getData();
        String hiddenData = qrView.getHiddenData();

        //Show a popup if there are problems with the data inputs
        if ("".equals(data)) {
            this.qrView.showMessage("Veuillez entrer des donnes !");
        } else if (4 * hiddenData.length() > data.length()) {
            this.qrView.showMessage("Le QRcode ne peut pas contenir toutes les données cachées, veuillez réduire la taille de ces données");
        } //Set data and hidden data, and generate a new QRcode
        else {
            this.qrcModel.setData(data);
            this.qrcModel.setHiddenData(hiddenData);
            Bitmap generateBitmap = this.qrcModel.generateBitmap();
            //Update the display
            this.qrView.Update(new BitmapDrawable(generateBitmap));
        }
    }

    /**
     * Save the QRcode generated, when the button "save" has been clicked
     */
    public void savePicture() {
        try {
            Bitmap bit = qrcModel.getBitmap();
            Activity activity = qrView.getActivity();

            //Generate a Calendar variable to set the name of the saved file
            Calendar c = Calendar.getInstance();
            int seconds = c.get(Calendar.SECOND);
            int minutes = c.get(Calendar.MINUTE);
            int hour = c.get(Calendar.HOUR);
            int day = c.get(Calendar.DAY_OF_MONTH);
            int month = c.get(Calendar.MONTH);
            int year = c.get(Calendar.YEAR);

            String timeStamp =
                    Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day) + "_"
                    + Integer.toString(hour) + ":" + Integer.toString(minutes) + ":" + Integer.toString(seconds);

            //Save the Bitmap QRcode
            String url = MediaStore.Images.Media.insertImage(activity.getContentResolver(), bit, "QRCode" + timeStamp, "Qr code auto-generated");
        } catch (Exception ex) {
            Logger.getLogger(QRCodeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Analyse the Bitmap image load to find the data and the hidden data
     */
    public void analyseImage(Bitmap yourSelectedImage) {
        com.google.zxing.qrcode.encoder.DataInQRCode.emptyHiddenDataEmmbeddedInQRCode();
        RGBLuminance source = new RGBLuminance(yourSelectedImage);
        GlobalHistogramBinarizer binarizer = new GlobalHistogramBinarizer(source);
        BinaryBitmap bb = new BinaryBitmap(binarizer);
        try {
            //Read the QRcode with the Zxing library
            QRCodeReader codeReader = new QRCodeReader();
            //Decode the QRcode ridden with the Zxing library
            Result result = codeReader.decode(bb);
            //Ask to the view to display a result popup
            String showedData = result.toString();
            String hiddenData = com.google.zxing.qrcode.encoder.DataInQRCode.getHiddenDataEmmbeddedInQRCode();
            this.qrView.showMessage("Données claire : " + showedData + "\n\n" + "Données cryptées : " + hiddenData);
        } catch (NotFoundException ex) {
            this.qrView.showMessage("Aucun QRcode trouvé");
            Log.w("Notfound", "une execption est survenue " + ex.toString());
        } catch (ChecksumException ex) {
            this.qrView.showMessage("QRcode détecté mais non valide");
            Log.w("Checksum", "une execption est survenue " + ex.toString());
        } catch (FormatException ex) {
            this.qrView.showMessage("Problème de format de QRcode");
            Log.w("FormatEx", "une execption est survenue " + ex.toString());
        } catch (Exception ex) {
            this.qrView.showMessage("Une erreur inconnue est survenue");
            Log.w("OtherEx", "une execption est survenue " + ex.toString());
        }
    }

    /**
     * Get the QRcode and ask to the view to displpay the QRcode when the phone
     * has been rotated
     */
    public void askForUpdate() {
        Bitmap bit = this.qrcModel.getBitmap();
        if (bit != null) {
            this.qrView.Update(new BitmapDrawable(bit));
        }

    }

    /**
     * All getters and setters
     */
    public static QRCodeController getController(QRCodeView in) {
        if (qrController == null) {
            qrController = new QRCodeController(in);
        }
        return qrController;
    }

    public void setActiveView(QRCodeView qrcvodeView) {
        this.qrView = qrcvodeView;
    }

    //Kill the current controller
    public void destroyController() {
        qrController = null;
    }
}
