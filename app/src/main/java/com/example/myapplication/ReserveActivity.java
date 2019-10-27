package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class ReserveActivity extends AppCompatActivity {

    MqttNetwork client;
    Button submit;
    String clientId;
    String fromTime_txt;
    String toTime_txt;
    String sector;
    TextView nameVar;
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


    int Year, Month, Date, beforeHrs, afterHrs, beforeMin, afterMin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserv);

        Intent i = getIntent();
        clientId = i.getStringExtra("userId");

        nameVar = findViewById(R.id.nameVariable);
        nameVar.setText(clientId);

        client = new MqttNetwork(ReserveActivity.this, clientId);


        dateButton = findViewById(R.id.dateButton);
        fromTime = findViewById(R.id.fromTime);
        toTime = findViewById(R.id.toTime);
        textDate = findViewById(R.id.textDate);
        toText = findViewById(R.id.toText);
        fromText = findViewById(R.id.fromText);

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
        submit = findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar beforeDate = Calendar.getInstance();
                Calendar afterDate = Calendar.getInstance();

                beforeDate.set(Year, Month, Date, beforeHrs, beforeMin);
                afterDate.set(Year, Month, Date, afterHrs, afterMin);

                startTime = beforeDate.getTimeInMillis() / 1000;
                finishTime = afterDate.getTimeInMillis() / 1000;

                dataFill();
            }
        });
    }

    public void dataFill() {
        EditText seats_txt;
        EditText sector_txt;
        Switch projector_switch;
        Switch secret_switch;
        Switch video_switch;
        Switch whiteboard_switch;
        Switch smartboard_switch;

        seats_txt = findViewById(R.id.seats);
        projector_switch = findViewById(R.id.Projector);
        secret_switch = findViewById(R.id.secret);
        video_switch = findViewById(R.id.videoCall);
        whiteboard_switch = findViewById(R.id.whiteboard);
        smartboard_switch = findViewById(R.id.smartBoard);
        sector_txt = findViewById(R.id.sectionVariable);

        seats = Integer.parseInt(seats_txt.getText().toString());
        fromTime_txt = fromText.getText().toString();
        toTime_txt = toText.getText().toString();
        sector = sector_txt.getText().toString();

        if (projector_switch.isChecked())
            projector = 1;
        else
            projector = 0;

        if (secret_switch.isChecked())
            secret = 1;
        else
            secret = 0;

        if (video_switch.isChecked())
            video = 1;
        else
            video = 0;

        if (whiteboard_switch.isChecked())
            whiteboard = 1;
        else
            whiteboard = 0;

        if (smartboard_switch.isChecked()) {
            smartboard = 1;
        } else
            smartboard = 0;

        client.MqttFindRoom(seats, projector, secret, video, whiteboard, smartboard, sector, startTime, finishTime);

    }


    private void handleDateButton() {

        Calendar calendar = Calendar.getInstance();

        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DAY = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                String dateString = year + "." + month + "." + date;
                textDate.setText(dateString);

                Year = year;
                Month = month;
                Date = date;

                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH, month);
                calendar1.set(Calendar.DATE, date);

                CharSequence dateCharSequence = DateFormat.format("EEEE, dd MMM yyyy", calendar1);

                textDate.setText(dateCharSequence);

            }
        }, YEAR, MONTH, DAY);

        datePickerDialog.show();

    }

    private void handlefromTime() {

        Calendar calendar = Calendar.getInstance();

        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                beforeHrs = hour;
                beforeMin = minute;

                String timeString = hour + ":" + minute;
                fromText.setText(timeString);
            }
        }, HOUR, MINUTE, true);

        timePickerDialog.show();

    }

    private void handletoTime() {

        Calendar calendar = Calendar.getInstance();

        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                afterHrs = hour;
                afterMin = minute;

                String timeString = hour + ":" + minute;
                toText.setText(timeString);
            }
        }, HOUR, MINUTE, true);

        timePickerDialog.show();

    }
}


