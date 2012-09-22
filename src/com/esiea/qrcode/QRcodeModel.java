/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esiea.qrcode;

import android.graphics.Bitmap;
import android.graphics.Color;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.EnumMap;

public class QRcodeModel implements IQRCodeModel
{

    public String data;
    public String hiddenData;
    private Bitmap bitmap;

    public Bitmap  generateBitmap()
    {

//get a byte matrix for the data
        BitMatrix matrix;
        com.google.zxing.Writer writer = new QRCodeWriter();
        com.google.zxing.Reader reader = new QRCodeMultiReader();
        try
        {
            int width = 200;
            int height = 200;
            QRCodeWriter codeWriter = new QRCodeWriter();
            EnumMap<EncodeHintType, Object> hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            com.google.zxing.qrcode.encoder.DataInQRCode.setData(this.data);
            com.google.zxing.qrcode.encoder.DataInQRCode.setHiddenData(this.hiddenData);
            matrix = codeWriter.encode(com.google.zxing.qrcode.encoder.DataInQRCode.getData(), BarcodeFormat.QR_CODE, width, height, hints);
            //matrix = codeWriter.encode(data, BarcodeFormat.QR_CODE, width, height,hints);
        }
        catch (com.google.zxing.WriterException e)
        {
            return null;
        }

//generate an image from the byte matrix
        int width = matrix.getWidth();
        int height = matrix.getHeight();

//create buffered image to draw to
        Bitmap  image;
        image = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

//iterate through the matrix and draw the pixels to the image
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                int grayValue;// = (int) (matrix.get(x, y)) & 0xff;
                if (matrix.get(x, y) == false)
                {
                    grayValue = 0xff;
                    //  grayValue = x;
                }
                else
                {
                    grayValue = 0;
                }
                image.setPixel(x, y, grayValue == 0 ? Color.BLACK : Color.WHITE);
                //  image.setRGB(x, y, grayValue);
            }
        }
        this.bitmap=image;
        return image;

    }

    public void setData(String data)
    {
        this.data = data;
    }

    public void setHiddenData(String hiddenData)
    {
        this.hiddenData = hiddenData;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

}
