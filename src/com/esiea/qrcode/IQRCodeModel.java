/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esiea.qrcode;

import android.graphics.Bitmap;
import com.google.zxing.FormatException;

public interface IQRCodeModel {

    public Bitmap  test();
    
    public void setData(String string);
        
}
