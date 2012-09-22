/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esiea.qrcode;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;

/**
 *
 * @author Petiois
 */
public class QRCodeView implements IView
{
    private final MainActivity view;

    QRCodeView(MainActivity aThis)
    {
        this.view = aThis;
    }

    public void Update(BitmapDrawable bitmap)
    {
        this.view.Update(bitmap);
    }

    public String getData()
    {
        return this.view.getData();
    }

    public String getHiddenData()
    {
        return this.view.getHiddenData();
    }

    public Activity getActivity()
    {
        return this.view;
    }

    public void showMessage(String message)
    {
        this.view.showMessage(message);
    }

}
