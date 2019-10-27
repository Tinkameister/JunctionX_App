package com.example.myapplication;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MqttResponder extends AppCompatActivity {
    public void FindRoomResponse(Context context, String payload) {
        payload = payload.replace("[", "");
        payload = payload.replace("]", "");

        int size = 0;
        String[] finalPayload = payload.split(",");
        Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
        int[] roomArray = new int[payload.length()];
        for(int i = 0; i < payload.length(); i++) {
            roomArray[i] = Integer.parseInt(finalPayload[i]);
            size = i;
        }
        Intent oneresultIntent = new Intent(context, oneResultActivity.class);
        Intent manyresultIntent = new Intent(context, manyResultsActivity.class);


        //noResult
        if(size == 0){
            Toast.makeText(context, "itt bent", Toast.LENGTH_SHORT).show();
            Intent noresultIntent = new Intent(context, noResult.class);
            startActivity(noresultIntent);
        }

        //oneResult
        if(size == 1){
            oneresultIntent.putExtra("response", roomArray);
            //startActivity(oneresultIntent);
        }

        //manyResult
        if(size > 1) {
            manyresultIntent.putExtra("response", roomArray);
            //startActivity(manyresultIntent);
        }
    }

    public static void ReserveRoomResponse(String payload) {
//ide kell írni a válaszra reakciókat, a KALENDÁR KIRÖLTŐT IS
    }

    public static void AddParticipantResponse(String payload) {

    }

    public static void QueryReserveTimesResponse(String payload) {
    }
}


