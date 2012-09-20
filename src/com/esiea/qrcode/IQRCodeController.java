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
public interface IQRCodeController {
    
    public Bitmap updateQRCode(String data,String Hiddendata);
}
