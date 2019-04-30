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
    Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final BarcodeDetector detector = new BarcodeDetector.Builder(getApplicationContext()).
                setBarcodeFormats(Barcode.DATA_MATRIX | Barcode.QR_CODE | Barcode.CODE_128
                        | Barcode.EAN_8 | Barcode.EAN_13 | Barcode.UPC_A | Barcode.UPC_E | Barcode.CODE_39 | Barcode.CODE_93
                        | Barcode.CODE_128 | Barcode.ITF | Barcode.CODABAR | Barcode.PDF417 | Barcode.ITF)
                .build();

        if(!detector.isOperational()) {
            textView.setText("Could not set up the detector!");
            return;
        }

        imageView = findViewById(R.id.imgview);
        textView = findViewById(R.id.txtContent);

        bitmap = BitmapFactory.
                decodeResource(getApplicationContext().getResources(), R.drawable.bar_2);
        imageView.setImageBitmap(bitmap);


        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                SparseArray<Barcode> barcode = detector.detect(frame);
                Barcode thisCode = null;
                if(barcode.size() > 0) {
                    for(int i = 0; i <= barcode.size() - 1; i++) {
                        thisCode = barcode.valueAt(i);
                        textView.setText(thisCode.rawValue + " \n ");
                    }
//                    Barcode thisCode = barcode.valueAt(0);
//                    textView.setText(thisCode.rawValue);
//                    textView.setText("\n");
                } else {
                    textView.setText("Could not read barcode, Please try again!!");
                }
            }
        });


    }
}
