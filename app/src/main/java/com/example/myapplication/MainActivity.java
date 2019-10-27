package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;




public class MainActivity extends AppCompatActivity {

    private Button b, c;
    Intent MapActivityIntent, RoomActivityIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent LoginActivityIntent = getIntent();
        final String clientId = LoginActivityIntent.getStringExtra("userId");

        MapActivityIntent = new Intent(MainActivity.this, MapActivity.class);
        RoomActivityIntent = new Intent(MainActivity.this, RoomActivity.class);

        b = findViewById(R.id.mapButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(MapActivityIntent);
            }
        });

        c = findViewById(R.id.reservationButton);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RoomActivityIntent.putExtra("userId", clientId);
                startActivity(RoomActivityIntent);
            }
        });

    }
}
