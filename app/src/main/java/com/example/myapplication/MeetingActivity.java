package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MeetingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);
    }

//    if(int a=8){
//        int i=0;
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT);
//        Button btn = new Button(this);
//        btn.setId(i);
//        i++;
//        final int id_ = btn.getId();
//        btn.setText("IDE");                                        //----->ide jön a foglalt szobaszám és az időpont(dátum és kezdő idő)
//        btn.setBackgroundColor(Color.rgb(70, 80, 90));
//        linear.addView(btn, params);
//        btn1 = ((Button) findViewById(id_));
//        btn1.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(),
//                        "Button clicked index = " + id_, Toast.LENGTH_SHORT)
//                        .show();
//            }
//        });
//    }


}

