package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class manyResultsActivity extends AppCompatActivity {

    Button h, t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_many_results);

        h = findViewById(R.id.modifyButton);
        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(manyResultsActivity.this, ReserveActivity.class);
                startActivity(i);
            }
        });

        t = findViewById(R.id.chooseButton);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(manyResultsActivity.this, oneResultActivity.class);
                startActivity(i);
            }
        });

    }
}
