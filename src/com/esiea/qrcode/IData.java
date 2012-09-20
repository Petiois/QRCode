/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esiea.qrcode;

import android.widget.EditText;

/**
 *
 * @author Petiois
 */
public interface IData {
    
    public QRcodeModel model = new QRcodeModel();
    
    public void setNewData(EditText editText);
    
}
