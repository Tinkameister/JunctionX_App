package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.github.tlaabs.timetableview.Schedule;
import com.github.tlaabs.timetableview.Time;
import com.github.tlaabs.timetableview.TimetableView;

import java.util.Random;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;


public class CheckActivity extends AppCompatActivity {
    TimetableView timetable;

    //Button s, c;
    String payload;
    String id;

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        timetable = findViewById(R.id.timetable);
        //timetable.setHeaderHighlight(2);

        ArrayList<Schedule> schedules = new ArrayList<>();
        Schedule schedule = new Schedule();
        schedule.setClassTitle("Workshop"); // sets subject
        schedule.setClassPlace("Telekom BP F.76"); // sets place
        schedule.setProfessorName(id); // sets professor
        schedule.setStartTime(new Time(getRandomNumberInRange( 9,17),getRandomNumberInRange(0, 59))); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(getRandomNumberInRange( 9,17),getRandomNumberInRange(0, 59))); // sets the end of class time (hour,minute)
        schedule.setDay(getRandomNumberInRange(0,1));
        schedules.add(schedule);
        timetable.add(schedules);

        schedule.setClassTitle("Meeting"); // sets subject
        schedule.setClassPlace("Telekom BP Room C.27"); // sets place
        schedule.setProfessorName(id); // sets professor
        schedule.setStartTime(new Time(getRandomNumberInRange( 9,17),getRandomNumberInRange(0, 59))); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(getRandomNumberInRange( 9,17),getRandomNumberInRange(0, 59)));
        schedule.setDay(2);
        schedules.add(schedule);
        timetable.add(schedules);

        schedule.setClassTitle("Consultation"); // sets subject
        schedule.setClassPlace("Telekom BP Room A.9"); // sets place
        schedule.setProfessorName(id); // sets professor
        schedule.setStartTime(new Time(getRandomNumberInRange( 9,17),getRandomNumberInRange(0, 59))); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(getRandomNumberInRange( 9,17),getRandomNumberInRange(0, 59)));
        schedule.setDay(getRandomNumberInRange(3,4));
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

