/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esiea.qrcode;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;

public interface IView {

    public QRcodeModel model = new QRcodeModel();

    public String getData();

    public String getHiddenData();

    public void Update(BitmapDrawable bitmap);

    public Activity getActivity();

    public void showMessage(String message);
}
