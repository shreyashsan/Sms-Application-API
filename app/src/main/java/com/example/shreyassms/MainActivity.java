package com.example.shreyassms;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity implements Activity, Main {

        // initialize variable
        EditText editTextMessage,editTextNumber;
        Button btnsent;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);

        // assign variable
        editTextMessage = findViewById(R.id.editTextMessage);
        editTextNumber = findViewById(R.id.editTextNumber);
        btnsent = findViewById(R.id.btnsent);

        btnsent.setOnClickListener(view -> {
            //check condition for permission
            if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)
            ==PackageManager.PERMISSION_GRANTED){
                //when permission is granted
                //create a method
                sendSMS();
            }else{
                //when permission is not granted
                // request for permission
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.SEND_SMS},
                        100);
            }
        });
    }

    @Override
    public void onRequstPermissionRequest(int requestCode, @NonNull String[] permission, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permission,grantResults);
            //check condition
        getPackageManager();
        if(requestCode==100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            // permission is granted
            // call method
            sendSMS();
        }else {
            //when permission denied
            //display toast msg
            Toast.makeText(this,"Permission Denied!",Toast.LENGTH_SHORT).show();
        }
    }

    public void sendSMS(){

        String message = editTextMessage.getText().toString();
        String number = editTextNumber.getText().toString();

        //check condition if string is empty or not
        if(!message.isEmpty()) {
            // initialize SMS Manager
            SmsManager mySmsManager = SmsManager.getDefault();
            //send message
            mySmsManager.sendTextMessage(number, null, message, null, null);
            //display Toast msg
            Toast.makeText(this, "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
        }else{
            //when string is empty then display toast msg
            Toast.makeText(this,"Please enter number and message",Toast.LENGTH_SHORT).show();
        }
    }
}