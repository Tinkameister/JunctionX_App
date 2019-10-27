package com.example.myapplication;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class MqttResponder extends AppCompatActivity {
    public void FindRoomResponse(String payload) {
        payload = payload.replace("[", "");
        payload = payload.replace("]", "");

        String finalPayload = payload;

        Intent i = new Intent(MqttResponder.this, CheckActivity.class);
        i.putExtra("payload", finalPayload);
        startActivity(i);






    }
}
