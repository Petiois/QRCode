/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esiea.qrcode;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 *
 * @author Petiois
 */
public class QRCodeView implements IData, IHiddenData,IDisplayQRCode {

    public QRCodeController qrcodeController = new QRCodeController();
    public String newData;
    public String nexHiddenData;
           
    public Bitmap getQRCode() {
        return qrcodeController.updateQRCode(newData,nexHiddenData);
    }
    

    public void displayQRCode(Bitmap bit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public BitmapDrawable Update(){
        BitmapDrawable bitDraw = new BitmapDrawable(getQRCode());
        return bitDraw;
}

    public void setNewData(EditText editText) {
        this.newData=editText.getText().toString();
    }
    
    public void setHiddenData(EditText editText) {
        this.nexHiddenData=editText.getText().toString();
    }
        
}
