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
    String resPayload = "";
    int roomNumArray[];
    MqttNetwork client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserv);

        clientId = "kaki"; //MqttClient.generateClientId()
        public void onClick (View view){
            if (view.getId() == R.id.submitButton) {
                client = new MqttNetwork(ReservActivity.this, clientId);
            }
        }
    }
}


        /*void publish () {
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

        void subscribe () {
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
    }
}

