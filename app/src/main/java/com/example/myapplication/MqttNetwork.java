package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.view.View;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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

public class MqttNetwork {
    String clientId;
    MqttAndroidClient client;

    public void RequestType0(String requestType){
        String payload =
                    "{" +
                        "\"RequestType\":"+"," +
                        "\"Params\":{" +
                        "\"Seats\":10," +
                        "\"Projector\":0," +
                        "\"Secret\":1," +
                        "\"Video\":0," +
                        "\"Whiteboard\":0," +
                        "\"Smartboard\":0," +
                        "\"Sector\":\"A\"}," +
                        "\"Times\":{" +
                        "\"Start\":123456789," +
                        "\"End\":123456789}}";


    }

}
