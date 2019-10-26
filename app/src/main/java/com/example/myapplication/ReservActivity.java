package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;

public class ReservActivity extends AppCompatActivity {

    MqttNetwork client;
    Button submit;
    String clientId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserv);

        submit = findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client = new MqttNetwork(ReservActivity.this, clientId);
            }
        });
    }

    public void dataFill() {
        String name;
        int seats;
        int projector;
        int secret;
        int video;
        int whiteboard;
        int smartboard;
        char sector;

        EditText nameVar;
        EditText seats_txt;
        EditText sector_txt;
        Switch projector_switch;
        Switch secret_switch;
        Switch video_switch;
        Switch whiteboard_switch;
        Switch smartboard_switch;

        nameVar = findViewById(R.id.nameVariable);
        seats_txt = findViewById(R.id.seats);
        projector_switch = findViewById(R.id.Projector);
        secret_switch = findViewById(R.id.secret);
        video_switch = findViewById(R.id.videoCall);
        whiteboard_switch = findViewById(R.id.whiteboard);
        smartboard_switch = findViewById(R.id.smartBoard);

        name = nameVar.getText().toString();
        seats = Integer.parseInt(seats_txt.getText().toString());
        if(projector_switch.isChecked())
            projector = 1;
        else
            projector = 0;

        if(secret_switch.isChecked())
            secret = 1;
        else
            secret = 0;

        if(video_switch.isChecked())
            video = 1;
        else
            video = 1;

        if(whiteboard_switch.isChecked())
            whiteboard = 1;
        else
            whiteboard = 0;

        if(smartboard_switch.isChecked()){
            smartboard = 1;
        }
        else
            smartboard = 0;


        client.MqttFindRoom()(seats, projector, secret, video, whiteboard, smartboard, sector);
    }

}

