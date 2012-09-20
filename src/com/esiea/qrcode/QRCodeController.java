/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esiea.qrcode;

import android.graphics.Bitmap;

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
}
