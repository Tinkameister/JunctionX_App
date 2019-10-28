package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RoomActivity extends AppCompatActivity {

    private Button v, l;
    Intent RoomReservIntent, HowCheckIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        Intent incoming = getIntent();
        final String clientId = incoming.getStringExtra("userId");


        RoomReservIntent = new Intent(RoomActivity.this, ReserveActivity.class);
        HowCheckIntent = new Intent(RoomActivity.this, HowCheck.class);

        v = findViewById(R.id.roomReservButton);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RoomReservIntent.putExtra("message", clientId);
                startActivity(RoomReservIntent);
            }
        });

        l = findViewById(R.id.roomCheckButton);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(HowCheckIntent);

            }
        });

    }


}
