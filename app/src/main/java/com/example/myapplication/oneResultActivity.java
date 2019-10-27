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

public class oneResultActivity extends AppCompatActivity {

    Button p;
    TextView text;
    int[] received;
    String output;
    Intent rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_result);

        rec = getIntent();
        received = rec.getIntArrayExtra("response");

        output = Arrays.toString(received);

        output = output.replace("[", "");
        output = output.replace("]", "");

        text = findViewById(R.id.numberText);
        text.setText(output);

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
