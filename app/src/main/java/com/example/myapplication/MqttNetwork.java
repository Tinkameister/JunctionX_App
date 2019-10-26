package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
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
import org.eclipse.paho.client.mqttv3.internal.wire.MqttSubscribe;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;

public class MqttNetwork {
    MqttAndroidClient client;
    IMqttToken connectToken;
    IMqttToken subscribeToken;
    IMqttToken disconnectToken;



    public MqttNetwork(final Context context, String clientId) {
        client = new MqttAndroidClient(context, "tcp://100.98.11.17:1883", clientId);
        try {
            connectToken = client.connect();
            connectToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(context, exception.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void MqttDisconnect(context){
        try {
            disconnectToken = client.disconnect();
            disconnectToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(context, "Disconnect failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch(MqttException e){
            e.printStackTrace();
        }
    }
}

