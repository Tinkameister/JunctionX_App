package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class manyResultsActivity extends AppCompatActivity {

    Button h, t;
    ListView listView;
    String[] responseStringArray;
    int[] responseIntArray;
    TextView chosen;
    String chosen_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_many_results);

        Intent received = getIntent();
        responseIntArray = received.getIntArrayExtra("response");

        String temp = Arrays.toString(responseIntArray);
        responseStringArray = temp.substring(1, temp.length()-1).split(", ");

        chosen = findViewById(R.id.chosenText);


        listView = findViewById(R.id.list);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, responseStringArray);

        listView.setAdapter(adapter);

        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                chosen_item = parent.getItemAtPosition(position).toString();
                chosen.setText(chosen_item);
            }
        });

        h = findViewById(R.id.modifyButton);
        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(manyResultsActivity.this, ReserveActivity.class);
                startActivity(i);
            }
        });

        t = findViewById(R.id.chooseButton);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent f = new Intent(manyResultsActivity.this, reserveConfirmation.class);
                f.putExtra("message", chosen_item);
                startActivity(f);
            }
        });

    }
}
