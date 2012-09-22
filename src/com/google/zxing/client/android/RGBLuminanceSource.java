/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.zxing.client.android;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.google.zxing.LuminanceSource;
import java.io.FileNotFoundException;

public final class RGBLuminanceSource extends LuminanceSource
{
    private final byte[] luminances;

    public RGBLuminanceSource(Bitmap paramBitmap)
    {
        super(paramBitmap.getWidth(), paramBitmap.getHeight());
        int i = paramBitmap.getWidth();
        int j = paramBitmap.getHeight();
        int[] arrayOfInt = new int[i * j];
        paramBitmap.getPixels(arrayOfInt, 0, i, 0, 0, i, j);
        this.luminances = new byte[i * j];
        int m;
        int n;
        for (int k = 0; ; k++)
        {
            if (k >= j)
                return;
            m = k * i;
            n = 0;
            if (n < i)
                break;
        }
        int i1 = arrayOfInt[(m + n)];
        int i2 = 0xFF & i1 >> 16;
        int i3 = 0xFF & i1 >> 8;
        int i4 = i1 & 0xFF;
        if ((i2 == i3) && (i3 == i4))
            this.luminances[(m + n)] = (byte)i2;

//    while (true)
//    {
//      n++;
//      break;
//      this.luminances[(m + n)] = (byte)(i4 + (i3 + (i2 + i3)) >> 2);
//    }
    }

    public RGBLuminanceSource(String paramString)
    throws FileNotFoundException
    {
        this(loadBitmap(paramString));
    }

    private static Bitmap loadBitmap(String paramString)
    throws FileNotFoundException
    {
        Bitmap localBitmap = BitmapFactory.decodeFile(paramString);
        if (localBitmap == null)
            throw new FileNotFoundException("Couldn't open " + paramString);
        return localBitmap;
    }

    public byte[] getMatrix()
    {
        return this.luminances;
    }

    public byte[] getRow(int paramInt, byte[] paramArrayOfByte)
    {
        if ((paramInt < 0) || (paramInt >= getHeight()))
            throw new IllegalArgumentException("Requested row is outside the image: " + paramInt);
        int i = getWidth();
        if ((paramArrayOfByte == null) || (paramArrayOfByte.length < i))
            paramArrayOfByte = new byte[i];
        System.arraycopy(this.luminances, paramInt * i, paramArrayOfByte, 0, i);
        return paramArrayOfByte;
    }
}
