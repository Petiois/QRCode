/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.zxing.client.android;

import android.graphics.Bitmap;
import android.graphics.Color;
import com.google.zxing.LuminanceSource;

/**
 *
 * @author camille
 */
public final class RGBLuminance extends LuminanceSource
{
    Bitmap bit;
    byte[][] rowtable;
    byte[] matrix;

    public RGBLuminance(Bitmap bit)
    {
        super(bit.getWidth(), bit.getHeight());
        this.rowtable = new byte[bit.getHeight()][bit.getWidth()];
        this.matrix = new byte[bit.getHeight()*bit.getWidth()];
        for (int j=0; j<bit.getHeight(); j++)
        {
            for (int i=0; i<bit.getWidth(); i++)
            {
                int pixel = bit.getPixel(i, j);
                double redvalue = Color.red(pixel);
                double greenvalue = Color.green(pixel);
                double bluevalue = Color.blue(pixel);

                double luminance = 0.2126*redvalue+0.7152*greenvalue+0.0722*bluevalue;
                int intermediateLuminance = (int) luminance;
                if (luminance-intermediateLuminance>0.5)
                {
                    intermediateLuminance++;
                }
                if (intermediateLuminance >= 255)
                {
                    intermediateLuminance=255;
                }
                if (intermediateLuminance <=0)
                {
                    intermediateLuminance=0;
                }

                byte luminaceByte = (byte) intermediateLuminance;
                this.rowtable[j][i]=luminaceByte;
                this.matrix[j * bit.getWidth()+ i]=luminaceByte;
            }
        }
        this.bit=bit;
    }


    @Override
    public byte[] getRow(int y, byte[] row)
    {
        return this.rowtable[y];
    }

    @Override
    public byte[] getMatrix()
    {
        return this.matrix;
    }

}
