/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esiea.qrcode;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.util.Log;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.Result;
import com.google.zxing.client.android.RGBLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Petiois
 */
public class QRCodeController implements IQRCodeController
{

    public QRcodeModel qrcModel = new QRcodeModel();
    public QRCodeView qrView;

    public QRCodeController(QRCodeView in)
    {
        this.qrView=in;
    }

    public Bitmap updateQRCode(String data,String Hiddendata)
    {
        qrcModel.setData(data);
        return qrcModel.generateBitmap();
    }

    void generateAndDisplayQRcode()
    {
        String data = qrView.getData();
        String hiddenData = qrView.getHiddenData();
        this.qrcModel.setData(data);
        this.qrcModel.setHiddenData(hiddenData);
        Bitmap generateBitmap = this.qrcModel.generateBitmap();
        this.qrView.Update(new BitmapDrawable(generateBitmap));
    }

    void savePicture() {
        try {
            Bitmap bit = qrcModel.getBitmap();
            Activity activity = qrView.getActivity();
            Calendar c = Calendar.getInstance();
            
            int seconds = c.get(Calendar.SECOND);
            int minutes = c.get(Calendar.MINUTE);
            int hour = c.get(Calendar.HOUR);
            int day = c.get(Calendar.DAY_OF_MONTH);
            int month = c.get(Calendar.MONTH);
            int year = c.get(Calendar.YEAR);
            
            String timeStamp =
                    Integer.toString(year) + "-" + Integer.toString(month)   +"-" + Integer.toString(day) + "_" +
                    Integer.toString(hour) + ":" + Integer.toString(minutes) +":" + Integer.toString(seconds);
            String url = MediaStore.Images.Media.insertImage(activity.getContentResolver(), bit, "QRCode" + timeStamp, "Qr code auto-generated");
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(QRCodeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void analyseImage(Bitmap yourSelectedImage) 
    {
        com.google.zxing.qrcode.encoder.DataInQRCode.emptyHiddenDataEmmbeddedInQRCode();
        RGBLuminanceSource source = new RGBLuminanceSource(yourSelectedImage);
        GlobalHistogramBinarizer binarizer = new GlobalHistogramBinarizer(source);
        BinaryBitmap bb = new BinaryBitmap(binarizer);
        try {
            BitMatrix blackMatrix = bb.getBlackMatrix();
            QRCodeReader codeReader = new QRCodeReader();
            Result result = codeReader.decode(bb);
            String showedData = result.toString();
            String hiddenData = com.google.zxing.qrcode.encoder.DataInQRCode.getHiddenDataEmmbeddedInQRCode();
            Log.w("toto", "showedData" + showedData);
            Log.w("toto", "hiddenData" + hiddenData);
            Log.w("toto", "test");
            int debug = 1;

        } catch (Exception ex) {
            Log.w("toto","une execption est survenue "+ ex.toString());
        }
    }
}
