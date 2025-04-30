package com.example.dell.DDAPP;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView WelcomeMsg;
        WelcomeMsg = findViewById(R.id.tvWelcomeMsg);
        WelcomeMsg.setText("Successfull Login");

    }
}
