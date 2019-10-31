package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LogInActivity extends AppCompatActivity {
    Intent MainActivityIntent;

    EditText logIn;
    Button loginButton;
    String Id;
    ReserveActivity reservation;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        reservation = new ReserveActivity();

        logIn = findViewById(R.id.loginText);
        loginButton = findViewById(R.id.loginButton);

        MainActivityIntent = new Intent(LogInActivity.this, MainActivity.class);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Id = logIn.getText().toString();
                MainActivityIntent.putExtra("userId", Id);
                startActivity(MainActivityIntent);
            }
        });
    }
}
