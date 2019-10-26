package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
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
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

public class ReservActivity extends AppCompatActivity {

    MqttNetwork client;
    Button submit;
    String clientId;
    String name;
    String fromTime_txt;
    String toTime_txt;
    String sector;
    int seats;
    int projector;
    int secret;
    int video;
    int whiteboard;
    int smartboard;
    long startTime;
    long finishTime;

    Button dateButton, fromTime, toTime;
    TextView fromText, toText, textDate;



    int Year, Month, Date, beforeHrs, afterHrs,  beforeMin, afterMin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserv);

        submit = findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar beforeDate = Calendar.getInstance();
                Calendar afterDate = Calendar.getInstance();

                beforeDate.set(Year, Month, Date, beforeHrs, beforeMin);
                afterDate.set(Year, Month, Date, afterHrs, afterMin);

                startTime = beforeDate.getTimeInMillis()/1000;
                finishTime = afterDate.getTimeInMillis()/1000;


                client = new MqttNetwork(ReservActivity.this, clientId);
                client.MqttFindRoom(seats, projector, secret, video, whiteboard, smartboard, sector, startTime, finishTime);
            }
        });
    }

    public void dataFill() {

        EditText nameVar;
        EditText seats_txt;
        EditText sector_txt;
        Switch projector_switch;
        Switch secret_switch;
        Switch video_switch;
        Switch whiteboard_switch;
        Switch smartboard_switch;

        dateButton = findViewById(R.id.dateButton);
        fromTime = findViewById(R.id.fromTime);
        toTime = findViewById(R.id.toTime);
        textDate = findViewById(R.id.textDate);
        toText = findViewById(R.id.toText);
        fromText = findViewById(R.id.fromText);
        nameVar = findViewById(R.id.nameVariable);
        seats_txt = findViewById(R.id.seats);
        projector_switch = findViewById(R.id.Projector);
        secret_switch = findViewById(R.id.secret);
        video_switch = findViewById(R.id.videoCall);
        whiteboard_switch = findViewById(R.id.whiteboard);
        smartboard_switch = findViewById(R.id.smartBoard);
        sector_txt = findViewById(R.id.sectionVariable);

        name = nameVar.getText().toString();
        seats = Integer.parseInt(seats_txt.getText().toString());
        fromTime_txt = fromText.getText().toString();
        toTime_txt = toText.getText().toString();
        sector = sector_txt.getText().toString();

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

                Year = year;
                Month = month;
                Date = date;

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

            beforeHrs = hour;
            beforeMin = minute;

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

                afterHrs = hour;
                afterMin = minute;

                String timeString=hour+":"+minute;
                toText.setText(timeString);
            }
        }, HOUR, MINUTE, true);

        timePickerDialog.show();

    }
}

