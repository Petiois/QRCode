/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esiea.qrcode;

import android.app.Activity;
import android.graphics.Bitmap;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Petiois
 */
public class QRCodeController implements IQRCodeController  {

    public QRcodeModel qrcModel = new QRcodeModel();
    
    public Bitmap updateQRCode(String data,String Hiddendata) {
        qrcModel.setData(data);
        return qrcModel.test();
    }
    
    public void saveQRCode(Activity activity) throws FileNotFoundException, IOException{
        qrcModel.saveQRCode(activity,qrcModel.getImage());
    }
}
