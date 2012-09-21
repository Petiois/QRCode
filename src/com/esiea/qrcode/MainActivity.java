package com.esiea.qrcode;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.FormatException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainActivity extends Activity
{
    QRCodeView qrcvodeView = new QRCodeView();
            
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
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
    case R.id.quit:
        this.finish();
    
    case R.id.load:
        
    }return true;}
    
    public void Update(BitmapDrawable bitdraw){
    ImageView imageView = (ImageView)this.findViewById(R.id.imageView1);
    imageView.setBackgroundDrawable(bitdraw);
}
    
    public EditText getData(){
        return (EditText)this.findViewById(R.id.editText1);
    }
    
    public EditText getHiddenData(){
        return (EditText)this.findViewById(R.id.EditText02);
    }
    
}

