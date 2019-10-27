package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.Arrays;

public class reserveConfirmation extends AppCompatActivity {

    Button button1;
    TextView textView;
    String incoming;
    Intent got;
    MqttNetwork client;
    String clientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_result);

        clientId = ReserveActivity.clientId;
        client = new MqttNetwork(this, clientId);

        got = getIntent();
        incoming = got.getStringExtra("message");

        textView = findViewById(R.id.numberText);
        textView.setText(incoming);

        button1 = findViewById(R.id.confirmButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(reserveConfirmation.this, MainActivity.class);
                startActivity(i);
            }
        });


    }

}
