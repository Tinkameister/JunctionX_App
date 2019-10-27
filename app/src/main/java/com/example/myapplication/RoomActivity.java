package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RoomActivity extends AppCompatActivity {

    private Button d, e;
    Intent ReserveActivityIntent, CheckActivityIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        Intent incoming = getIntent();
        final String clientId = incoming.getStringExtra("userId");

        ReserveActivityIntent = new Intent(RoomActivity.this, ReserveActivity.class);
        CheckActivityIntent = new Intent(RoomActivity.this, CheckActivity.class);

        d = findViewById(R.id.roomReservButton);
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReserveActivityIntent.putExtra("userId", clientId);
                startActivity(ReserveActivityIntent);
            }
        });

        e = findViewById(R.id.roomCheckButton);
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(CheckActivityIntent);
            }
        });
    }


}
