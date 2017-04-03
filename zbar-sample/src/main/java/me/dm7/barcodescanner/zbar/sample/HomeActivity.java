package me.dm7.barcodescanner.zbar.sample;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private static final int ZBAR_CAMERA_PERMISSION = 1;
    private Class<?> mClss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        try{
            Bundle b = getIntent().getExtras();
            String username = b.getString("username");
            TextView usernametxt = (TextView)findViewById(R.id.userText);
            usernametxt.setText("Benvenuto " + username + "!");
        }catch(Exception e){
            Log.d("BUNDLE","No username received!");
        }
    }

    //onClick del bottone
    public void Carico(View v){
        //   Intent intent = new Intent(v.getContext(), BarcodeScanner.class);
        //   startActivity(intent);
        launchActivity(SimpleScannerActivity.class);
    }



    //onClick del bottone
    public void Scarico(View v){

    }

    public void launchActivity(Class<?> clss) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            mClss = clss;
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, ZBAR_CAMERA_PERMISSION);
        } else {
            Intent intent = new Intent(this, clss);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String permissions[], int[] grantResults) {
        switch (requestCode) {
            case ZBAR_CAMERA_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(mClss != null) {
                        Intent intent = new Intent(this, mClss);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(this, "Please grant camera permission to use the QR Scanner", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }




}
