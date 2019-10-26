package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class ReservActivity extends AppCompatActivity {
    String clientId;
    MqttAndroidClient client;
    String resPayload = "";
    int roomNumArray[];
    Button dateButton, fromTime, toTime;
    TextView fromText, toText, textDate;

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

        // ------------------------ DATE selector --------------------------------------

        dateButton= findViewById(R.id.dateButton);
        fromTime= findViewById(R.id.fromTime);
        toTime= findViewById(R.id.toTime);
        textDate= findViewById(R.id.textDate);
        toText= findViewById(R.id.toText);
        fromText= findViewById(R.id.fromText);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDateButton();
            }
        });

        fromTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handlefromTime();
            }
        });

        toTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handletoTime();
            }
        });

    }

    private void handleDateButton(){

        Calendar calendar = Calendar.getInstance();

        int YEAR=calendar.get(Calendar.YEAR);
        int MONTH=calendar.get(Calendar.MONTH);
        int DAY=calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                String dateString=year+"."+month+"."+date;
                textDate.setText(dateString);

                Calendar calendar1=Calendar.getInstance();
                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH, month);
                calendar1.set(Calendar.DATE, date);

                CharSequence dateCharSecuence = DateFormat.format("EEEE, dd MMM yyyy", calendar1);

                textDate.setText(dateCharSecuence);

            }
        }, YEAR, MONTH, DAY);

        datePickerDialog.show();

    }

    private void handlefromTime(){

        Calendar calendar = Calendar.getInstance();

        int HOUR=calendar.get(Calendar.HOUR);
        int MINUTE=calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
            String timeString=hour+":"+minute;
            fromText.setText(timeString);
            }
        }, HOUR, MINUTE, true);

        timePickerDialog.show();

    }

    private void handletoTime(){

        Calendar calendar = Calendar.getInstance();

        int HOUR=calendar.get(Calendar.HOUR);
        int MINUTE=calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                String timeString=hour+":"+minute;
                toText.setText(timeString);
            }
        }, HOUR, MINUTE, true);

        timePickerDialog.show();

    }
}


