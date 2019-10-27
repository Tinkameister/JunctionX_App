package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.github.tlaabs.timetableview.Schedule;
import com.github.tlaabs.timetableview.Time;
import com.github.tlaabs.timetableview.TimetableView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;


public class CheckActivity extends AppCompatActivity {
    TimetableView timetable;

    //Button s, c;
    String payload;
    String id;
    int start_hour=15;
    int start_min=20;
    int end_hour=16;
    int end_min=40;
    ReserveActivity reservation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        Intent k = getIntent();
        payload = k.getStringExtra("payload");


        timetable = findViewById(R.id.timetable);
        //timetable.setHeaderHighlight(2);

        ArrayList<Schedule> schedules = new ArrayList<>();
        Schedule schedule = new Schedule();
        schedule.setClassTitle("Workshop"); // sets subject
        schedule.setClassPlace("Telekom BP F.76"); // sets place
        schedule.setProfessorName(id); // sets professor
        schedule.setStartTime(new Time(12,30)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(17,20)); // sets the end of class time (hour,minute)
        schedule.setDay(2);
        schedules.add(schedule);
        timetable.add(schedules);

        schedule.setClassTitle("Meeting"); // sets subject
        schedule.setClassPlace("Telekom BP Room C.27"); // sets place
        schedule.setProfessorName(id); // sets professor
        schedule.setStartTime(new Time(9,30)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(10,40)); // sets the end of class time (hour,minute)
        schedule.setDay(3);
        schedules.add(schedule);
        timetable.add(schedules);

        schedule.setClassTitle("Consultation"); // sets subject
        schedule.setClassPlace("Telekom BP Room A.9"); // sets place
        schedule.setProfessorName(id); // sets professor
        schedule.setStartTime(new Time(16,0)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(18,12)); // sets the end of class time (hour,minute)
        schedule.setDay(0);
        schedules.add(schedule);
        timetable.add(schedules);


        /*s = findViewById(R.id.leftButton);
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
        });*/
    }



}

