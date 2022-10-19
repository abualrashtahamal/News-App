package com.example.laststep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity3 extends AppCompatActivity {
    Button btnsport,btnhealth,btnmedical,myprofile;
    private String getExtra ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        getExtra =  getIntent().getExtras().get("Id").toString();
        btnsport=(Button) findViewById(R.id.buttonsport);
        btnsport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity4.class);
                startActivity(intent);
            }
        });
        btnhealth=(Button) findViewById(R.id.buttonhealth);
        btnhealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity5.class);
                startActivity(intent);
            }
        });
        btnmedical=(Button) findViewById(R.id.buttonmedical);
        btnmedical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity6.class);
                startActivity(intent);
            }
        });
        myprofile=(Button) findViewById(R.id.myprofile);
        myprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity7.class);
                intent.putExtra("Id",getExtra);
                startActivity(intent);
            }
        });
        System.out.println("Activity3_ID :" + getExtra);
    }


}