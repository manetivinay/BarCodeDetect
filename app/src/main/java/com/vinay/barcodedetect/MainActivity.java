package com.vinay.barcodedetect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public class MainActivity extends AppCompatActivity {

    Button button;
    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imgview);
        textView = findViewById(R.id.txtContent);

        Bitmap bitmap = BitmapFactory.
                decodeResource(getApplicationContext().getResources(), R.drawable.puppy);
        imageView.setImageBitmap(bitmap);

        BarcodeDetector detector = new BarcodeDetector.Builder(getApplicationContext()).
                setBarcodeFormats(Barcode.DATA_MATRIX | Barcode.QR_CODE)
                .build();
        if(!detector.isOperational()){
            textView.setText("Could not set up the detector!");
            return;
        }

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

            }
        });

        Frame frame = new Frame.Builder().setBitmap(bitmap).build();
        SparseArray<Barcode> barcode = detector.detect(frame);

        Barcode thisCode = barcode.valueAt(0);
        textView.setText(thisCode.rawValue);


    }
}
