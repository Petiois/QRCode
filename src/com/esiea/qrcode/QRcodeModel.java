/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esiea.qrcode;

import android.graphics.Bitmap;
import android.graphics.Color;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.UnsupportedEncodingException;

public class QRcodeModel implements IQRCodeModel  {
  
    public String data;
    public String hiddenData;
    
    public Bitmap  test() {
//           byte[] b = {0x48, 0x45, 0x4C, 0x4C, 0x4F};
////convert the byte array into a UTF-8 string
//        try {
//            data = new String(b, "UTF8");
//            data = "http://www.google.com";
//        } catch (UnsupportedEncodingException e) {
//            //the program shouldn't be able to get here
//            return null;
//        }
            
//get a byte matrix for the data
        BitMatrix matrix;
        com.google.zxing.Writer writer = new QRCodeWriter();
        com.google.zxing.Reader reader = new QRCodeMultiReader();
        try {
            int width = 200;
            int height = 200;
            matrix = writer.encode(data, com.google.zxing.BarcodeFormat.QR_CODE, width, height);
        } catch (com.google.zxing.WriterException e) {
            return null;
        }

//generate an image from the byte matrix
        int width = matrix.getWidth();
        int height = matrix.getHeight();

//create buffered image to draw to
        Bitmap  image;
        image = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

//iterate through the matrix and draw the pixels to the image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int grayValue;// = (int) (matrix.get(x, y)) & 0xff;
                if (matrix.get(x, y) == false) {
                    grayValue = 0xff;
                    //  grayValue = x;
                } else {
                    grayValue = 0;
                }
                image.setPixel(x, y, grayValue == 0 ? Color.BLACK : Color.WHITE);
                //  image.setRGB(x, y, grayValue);
            }
        }
        int sizex = 2 * 2 * 2 * 2;
        int sizey = 2 * 2 * 2 * 2;
        for (int y = height / 2 - sizey; y < height / 2 + sizey; y++) {
            for (int x = width / 2 - sizex; x < width / 2 + sizex; x++) {
                int grayValue;// = (int) (matrix.get(x, y)) & 0xff;

                double toto = Math.random();
                int tata = 0;
                if (toto > 0.5) {
                    grayValue = 0;
                } else {
                    grayValue = 0xff;
                }

                image.setPixel(x, y, (grayValue == 0 ? Color.BLACK : Color.WHITE));

            }
        }
        return image;

    }

    public void setData(String data) {
        this.data = data;
    }
   
    public void setHiddenData(String hiddenData) {
        this.hiddenData = hiddenData;
    }
    
}
