package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import static java.lang.Integer.parseInt;

public class HowCheck extends AppCompatActivity {

    MqttNetwork client;
    private EditText v;
    Button t;
    Intent NumberCheckIntent, SendRoomNumber;
    String clientId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_check);

        Intent i = getIntent();
        clientId = i.getStringExtra("userId");

        if(client == null) {
            client = new MqttNetwork(HowCheck.this, clientId);
        }

        Intent incoming = getIntent();
        final String clientId = incoming.getStringExtra("userId");

        NumberCheckIntent = new Intent(HowCheck.this, CheckActivity.class);



        t = findViewById(R.id.numberButton);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(NumberCheckIntent);
                v = findViewById(R.id.CheckRoomText);
                String content = v.getText().toString();


                //MqttResponder

                long today=System.currentTimeMillis()/1000;

                client.MqttQueryReserveTimes(parseInt(content), today+84600, today+604800+84600);
            }
        });

    }
}
