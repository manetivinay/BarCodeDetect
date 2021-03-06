package com.vinay.barcodedetect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public class ScanFromCamera extends AppCompatActivity {

    TextView barcodeInfo;
    SurfaceView cameraView;
    CameraSource cameraSource;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_from_camera);

        cameraView = (SurfaceView) findViewById(R.id.camera_view);
        barcodeInfo = (TextView) findViewById(R.id.txtContent);


        BarcodeDetector barcodeDetector =
                new BarcodeDetector.Builder(this)
                        .setBarcodeFormats(Barcode.DATA_MATRIX | Barcode.QR_CODE | Barcode.CODE_128
                                | Barcode.EAN_8 | Barcode.EAN_13 | Barcode.UPC_A | Barcode.UPC_E | Barcode.CODE_39 | Barcode.CODE_93
                                | Barcode.CODE_128 | Barcode.ITF | Barcode.CODABAR | Barcode.PDF417 | Barcode.ITF)
                        .build();

        cameraSource = new CameraSource
                .Builder(this, barcodeDetector)
                .setRequestedPreviewSize(640, 480)
                .build();

        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder){

                try {
                    cameraSource.start(cameraView.getHolder());
                } catch(IOException ie) {
                    Log.e("CAMERA SOURCE", ie.getMessage());
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder){
                cameraSource.stop();
            }
        });


        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release(){
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections){

                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                if(barcodes.size() != 0) {
                    barcodeInfo.post(new Runnable() {    // Use the post method of the TextView
                        public void run(){
                            barcodeInfo.setText(    // Update the TextView
                                    barcodes.valueAt(0).displayValue
                            );
                        }
                    });
                }
            }
        });
    }
}