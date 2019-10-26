package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.DownloadManager;
import android.content.Context;
import android.text.PrecomputedText;
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
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttSubscribe;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import android.os.AsyncTask;

public class MqttNetwork {
    MqttAndroidClient client;
    IMqttToken connectToken;
    IMqttToken subscribeToken;
    IMqttToken disconnectToken;

    String clientID;
    Context myContext;

    String response;
    boolean newResponse = false;

    public MqttNetwork(Context context, String clientId) {
        myContext = context;
        //Connect to MQTT broker
        clientID = clientId;
        client = new MqttAndroidClient(myContext, "tcp://100.98.11.17:1883", clientId);
        try {
            connectToken = client.connect();
            connectToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(myContext, "Connected", Toast.LENGTH_SHORT).show();

                    //Subscribe to response subtopic
                    String topic = "users/" + clientID + "/response";

                    try{
                        subscribeToken = client.subscribe(topic, 0);
                    } catch (MqttException ex){
                        ex.printStackTrace();
                    }

                    subscribeToken.setActionCallback(new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            Toast.makeText(myContext, "Subscribed", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Toast.makeText(myContext, "Subscribe failed", Toast.LENGTH_SHORT).show();
                        }
                    });

                    client.setCallback(new MqttCallback() {
                        @Override
                        public void connectionLost(Throwable cause) {
                            Toast.makeText(myContext, "Connection lost", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void messageArrived(String topic, MqttMessage message) throws Exception {
                            response = new String(message.getPayload());
                            newResponse = true;
                        }

                        @Override
                        public void deliveryComplete(IMqttDeliveryToken token) {

                        }
                    });
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void Publish(String payload) {
        MqttMessage message = new MqttMessage();
        message.setPayload(payload.getBytes());
        try {
            client.publish("users/" + clientID + "/response", message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void Disconnect(){
        try {
            disconnectToken = client.disconnect();
            disconnectToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(myContext, "Disconnected", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(myContext, "Disconnect failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch(MqttException e){
            e.printStackTrace();
        }
    }

    private class AsyncResponseWaiter extends AsyncTask <Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            while (!newResponse) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            newResponse = false;
            return response;
        }
    }

    public String MqttFindRoom(int seats, int projector, int secret, int video, int whiteboard, int smartboard, String sector, long startTime, long endTime){

        String payload = "{\"RequestType\":0," +
                "\"Params\":{" +
                "\"Seats\":"+ seats + "," +
                "\"Projector\":" + projector + "," +
                "\"Secret\":" + secret + "," +
                "\"Video\":" + video + "," +
                "\"Whiteboard\":" + whiteboard + "," +
                "\"Smartboard\":" + smartboard + "," +
                "\"Sector\":\"" + sector + "\"}," +
                "\"Times\":{" +
                "\"Start\":" + startTime + "," +
                "\"End\":" + endTime + "}}";

        Publish(payload);

        return new AsyncResponseWaiter().doInBackground();
    }

    public String MqttQueryReserveTimes(int roomID, long startTime, long endTime){

        String payload = "{\"RequestType\":1," +
                "\"RoomID\":" + roomID + "," +
                "\"Times\":{" +
                "\"Start\":" + startTime + "," +
                "\"End\":" + endTime + "}}";

        Publish(payload);

        return new AsyncResponseWaiter().doInBackground();
    }

    public String MqttReserveRoom(int roomID, long startTime, long endTime){

        String payload = "{\"RequestType\":2," +
                "\"RoomID\":" + roomID + "," +
                "\"Times\":{" +
                "\"Start\":" + startTime + "," +
                "\"End\":" + endTime + "}}";

        Publish(payload);

        return new AsyncResponseWaiter().doInBackground();
    }

    public String MqttAddParticipant(int reserveID, String participant){

        String payload = "{\"RequestType\":3," +
                "\"ReserveID\":" + reserveID + "," +
                "\"Participant\":\"" + participant + "\"}";

        Publish(payload);

        return new AsyncResponseWaiter().doInBackground();
    }
}

