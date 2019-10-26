package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RoomActivity extends AppCompatActivity {

    private Button d, e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        d = findViewById(R.id.roomReservButton);
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(RoomActivity.this, ReservActivity.class);
                startActivity(k);
            }
        });

        e = findViewById(R.id.roomCheckButton);
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent l = new Intent(RoomActivity.this, CheckActivity.class);
                startActivity(l);
            }
        });
    }


}
