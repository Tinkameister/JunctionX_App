package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class noResult extends AppCompatActivity {

    Button f;
    //Intent Modify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_result);

        //Modify = new Intent(noResult.this, ReservActivity.class);

        f = findViewById(R.id.modifyParButton);
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(Modify);
            }
        });

    }
}
