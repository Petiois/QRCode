package com.esiea.qrcode;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.google.zxing.FormatException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainActivity extends Activity
{
    boolean exit = true;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Update();
    }
    
    public void Update(){
    Bitmap bit = null;
        try {
            bit = TestQRcode.test();
        } catch (FormatException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
    BitmapDrawable bitDraw = new BitmapDrawable(bit);
    TextView textView = (TextView)this.findViewById(R.id.mainTextView);
    textView.setBackgroundDrawable(bitDraw);
}
    
}

