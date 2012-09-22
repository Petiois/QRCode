/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esiea.qrcode;

public interface IView
{

    public QRcodeModel model = new QRcodeModel();

    public String getData();

    public String getHiddenData();

}
