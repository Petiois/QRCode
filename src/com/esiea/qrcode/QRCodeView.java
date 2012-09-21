/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esiea.qrcode;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Petiois
 */
public class QRCodeView implements IView {

    public QRCodeController qrcodeController = new QRCodeController();
    public String newData;
    public String nexHiddenData;
           
    
    public BitmapDrawable Update(){
        Bitmap bit = null;
        bit = qrcodeController.updateQRCode(newData, nexHiddenData);
        BitmapDrawable bitDraw = new BitmapDrawable(bit);
        return bitDraw;
}

    public void setNewData(EditText editText) {
        this.newData=editText.getText().toString();
    }
    
    public void setHiddenData(EditText editText) {
        this.nexHiddenData=editText.getText().toString();
    }

    public void saveQRCode(Activity activity) {
        try {
            qrcodeController.saveQRCode(activity);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(QRCodeView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(QRCodeView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
}
