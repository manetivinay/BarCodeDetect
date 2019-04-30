package com.vinay.barcodedetect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OptionsToSacn extends AppCompatActivity {

    Button scanFromCamera, readStaticImage;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_to_sacn);


        scanFromCamera = findViewById(R.id.scan_from_camera);
        readStaticImage = findViewById(R.id.scan_static_image);

        scanFromCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(OptionsToSacn.this, ScanFromCamera.class);
                startActivity(intent);
            }
        });

        readStaticImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(OptionsToSacn.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
