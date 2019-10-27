package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class oneResultActivity extends AppCompatActivity {

    Button p;
    TextView text;
    String received;
    Intent rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_result);

        rec = getIntent();
        received = rec.getStringExtra("response");

        text = findViewById(R.id.numberText);
        text.setText(received);

        p = findViewById(R.id.confirmButton);
        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(oneResultActivity.this, MainActivity.class);
                startActivity(i);
            }
        });


    }

}
