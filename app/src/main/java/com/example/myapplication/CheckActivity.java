package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.tlaabs.timetableview.Schedule;
import com.github.tlaabs.timetableview.Time;
import com.github.tlaabs.timetableview.TimetableView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;


public class CheckActivity extends AppCompatActivity {
    TimetableView timetable;

    Button s, c;
    String payload;
    String id;
    int start_hour;
    int start_min;
    int end_hour;
    int end_min;
    ReservActivity reservation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        Intent k = getIntent();
        payload = k.getStringExtra("payload");
        id = reservation.clientId;

        start_hour = reservation.beforeHrs;
        start_min = reservation.beforeMin;
        end_hour = reservation.afterHrs;
        end_min = reservation.beforeMin;





        timetable = findViewById(R.id.timetable);
        //timetable.setHeaderHighlight(2);

        ArrayList<Schedule> schedules = new ArrayList<Schedule>();
        Schedule schedule = new Schedule();
        schedule.setClassTitle("Meeting"); // sets subject
        schedule.setClassPlace("Telekom BP"); // sets place
        schedule.setProfessorName(id); // sets professor
        schedule.setStartTime(new Time(start_hour,start_min)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(end_hour,end_min)); // sets the end of class time (hour,minute)
        schedules.add(schedule);
        timetable.add(schedules);

        s = findViewById(R.id.leftButton);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ez lesz az adott hét mínusz 604800
            }
        });

        c = findViewById(R.id.rightButton);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ez lesz az adott hét plusz 604800
            }
        });
    }



}

