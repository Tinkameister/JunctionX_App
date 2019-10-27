package com.example.myapplication;

class MqttResponder {
    public static void FindRoomResponse(String payload) {
        payload = payload.replace("[", "");
        payload = payload.replace("]", "");

        String finalPayload = payload;
    }
}
