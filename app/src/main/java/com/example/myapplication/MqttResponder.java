package com.example.myapplication;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MqttResponder extends AppCompatActivity {
    public void FindRoomResponse(Context context, String payload) {
        payload = payload.replace("[", "");
        payload = payload.replace("]", "");


        String[] finalPayload = payload.split(",");
        int[] roomArray = new int[finalPayload.length];
        for(int i = 0; i < finalPayload.length; i++) {
            roomArray[i] = Integer.parseInt(finalPayload[i]);
        }
        Intent oneresultIntent = new Intent(context, oneResultActivity.class);
        Intent manyresultIntent = new Intent(context, manyResultsActivity.class);
        Intent noresultIntent = new Intent(context, noResult.class);


        //noResult
        if(roomArray.length == 0){
            context.startActivity(noresultIntent);
        }

        //oneResult
        if(roomArray.length == 1){
            oneresultIntent.putExtra("response", roomArray);
            context.startActivity(oneresultIntent);
        }

        //manyResult
        if(roomArray.length > 1) {
            manyresultIntent.putExtra("response", roomArray);
            context.startActivity(manyresultIntent);
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


