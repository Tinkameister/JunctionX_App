package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
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

public class ReservActivity extends AppCompatActivity {
    String clientId;
    MqttAndroidClient client;
    String resPayload = "";
    int roomNumArray[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserv);

        clientId = "kaki"; //MqttClient.generateClientId()
        client = new MqttAndroidClient(ReservActivity.this, "tcp://100.98.11.17:1883", clientId);

        try {
            IMqttToken connectToken = client.connect();
            connectToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(ReservActivity.this, "Connected", Toast.LENGTH_SHORT).show();
                    subscribe();
                    callBack();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(ReservActivity.this, "Not connected", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    void publish() {
        MqttMessage message = new MqttMessage();
        resPayload =
                "{" +
                        "\"RequestType\":0," +
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
        message.setPayload(resPayload.getBytes());
        try {
            client.publish("users/testuser1/request", message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    void subscribe() {
        String topic = "users/testuser1/response";
        int qos = 0;
        IMqttToken subToken = null;
        try {
            subToken = client.subscribe(topic, qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
        subToken.setActionCallback(new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Toast.makeText(ReservActivity.this, "Subscribed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Toast.makeText(ReservActivity.this, "Not subscribed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void callBack() {
        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                String received = new String(message.getPayload());
                String stringNumber = received.replace("[", "");
                stringNumber = stringNumber.replace("]", "");

                String[] parts = stringNumber.split(",");
                int[] roomArray = new int[parts.length];
                for (int i = 0; i < parts.length; i++) {
                    roomArray[i] = Integer.parseInt(parts[i]);
                }

                Toast.makeText(ReservActivity.this, stringNumber, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    }
}
