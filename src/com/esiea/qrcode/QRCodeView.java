/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esiea.qrcode;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;

/**
 *
 * @author Francois
 */
public class QRCodeView implements IView {

    private final MainActivity view;

    /**
     * Class constructor who instance an new view. Argument : a MainActivity
     */
    QRCodeView(MainActivity aThis) {
        this.view = aThis;
    }

    /**
     * Update the QRCode. Call the Update method of the MainActivity class, give
     * control to the MainActivity Argument : BitmapDraxable.
     */
    public void Update(BitmapDrawable bitmap) {
        this.view.Update(bitmap);
    }

    /**
     * All getters and setters of the class
     *
     */
    public String getData() {
        return this.view.getData();
    }

    public String getHiddenData() {
        return this.view.getHiddenData();
    }

    public Activity getActivity() {
        return this.view;
    }

    /**
     * Give control to the MainActivity to display a popup message which is the
     * argument "message". This method has been called by the controller
     *
     */
    public void showMessage(String message) {
        this.view.showMessage(message);
    }
}
