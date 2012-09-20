package com.esiea.qrcode;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.FormatException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainActivity extends Activity
{
    QRCodeView qrcvodeView = new QRCodeView();
    QRcodeModel model = new QRcodeModel();
            
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button bouton = (Button)findViewById(R.id.button1);
        bouton.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 qrcvodeView.setNewData(getData());
                 qrcvodeView.setHiddenData(getHiddenData());
                 Update(qrcvodeView.Update());
                 
             }
         });   
    }
    
    public void Update(BitmapDrawable bitdraw){
    TextView textView = (TextView)this.findViewById(R.id.mainTextView);
    textView.setBackgroundDrawable(bitdraw);
}
    
    public EditText getData(){
        return (EditText)this.findViewById(R.id.editText1);
    }
    
    public EditText getHiddenData(){
        return (EditText)this.findViewById(R.id.EditText02);
    }
    
}

