package com.example.myapplication;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        timetable = findViewById(R.id.timetable);
        //timetable.setHeaderHighlight(2);

        ArrayList<Schedule> schedules = new ArrayList<Schedule>();
        Schedule schedule = new Schedule();
        schedule.setClassTitle("Data Structure"); // sets subject
        schedule.setClassPlace("IT-601"); // sets place
        schedule.setProfessorName("Won Kim"); // sets professor
        schedule.setStartTime(new Time(10,0)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(13,30)); // sets the end of class time (hour,minute)
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

