package com.example.myapplication;
import android.content.Context;
import android.widget.Toast;
import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

enum CallbackResponseTypes{
    FindRoom, QueryReserveTimes, ReserveRoom, AddParticipant
}

public class MqttNetwork {
    MqttResponder responder;
    CallbackResponseTypes ResponseType;
    MqttAndroidClient client;
    IMqttToken connectToken;
    IMqttToken subscribeToken;
    IMqttToken disconnectToken;

    String clientID;
    Context myContext;

    public MqttNetwork(Context context, String clientId) {
        responder = new MqttResponder();
        myContext = context;
        //Connect to MQTT broker
        clientID = clientId;
        client = new MqttAndroidClient(myContext, "tcp://100.98.11.17:1883", clientId);
        try {
            connectToken = client.connect();
            connectToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(myContext, "Connected", Toast.LENGTH_SHORT).show();

                    //Subscribe to response subtopic
                    String topic = "users/" + clientID + "/response";

                    try{
                        subscribeToken = client.subscribe(topic, 0);
                    } catch (MqttException ex){
                        ex.printStackTrace();
                    }

                    subscribeToken.setActionCallback(new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            Toast.makeText(myContext, "Subscribed", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Toast.makeText(myContext, "Subscribe failed", Toast.LENGTH_SHORT).show();
                        }
                    });

                    client.setCallback(new MqttCallback() {
                        @Override
                        public void connectionLost(Throwable cause) {
                            Toast.makeText(myContext, "Connection lost", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void messageArrived(String topic, MqttMessage message) throws Exception {
                            String response = new String(message.getPayload());

                            switch (ResponseType){
                                case FindRoom:
                                    responder.FindRoomResponse(myContext, response);
                                case ReserveRoom:
                                    responder.ReserveRoomResponse(response);
                                case AddParticipant:
                                    responder.AddParticipantResponse(response);
                                case QueryReserveTimes:
                                    responder.QueryReserveTimesResponse(response);
                            }
                        }

                        @Override
                        public void deliveryComplete(IMqttDeliveryToken token) {

                        }
                    });
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void Publish(String payload) {
        MqttMessage message = new MqttMessage();
        message.setPayload(payload.getBytes());
        try {
            client.publish("users/" + clientID + "/request", message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void Disconnect(){
        try {
            disconnectToken = client.disconnect();
            disconnectToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(myContext, "Disconnected", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(myContext, "Disconnect failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch(MqttException e){
            e.printStackTrace();
        }
    }

    public void MqttFindRoom(int seats, int projector, int secret, int video, int whiteboard, int smartboard, String sector, long startTime, long endTime){

        String payload = "{\"RequestType\":0," +
                "\"Params\":{" +
                "\"Seats\":"+ seats + "," +
                "\"Projector\":" + projector + "," +
                "\"Secret\":" + secret + "," +
                "\"Video\":" + video + "," +
                "\"Whiteboard\":" + whiteboard + "," +
                "\"Smartboard\":" + smartboard + "," +
                "\"Sector\":\"" + sector + "\"}," +
                "\"Times\":{" +
                "\"Start\":" + startTime + "," +
                "\"End\":" + endTime + "}}";

        Publish(payload);

        ResponseType = CallbackResponseTypes.FindRoom;
    }

    public void MqttQueryReserveTimes(int roomID, long startTime, long endTime){

        String payload = "{\"RequestType\":1," +
                "\"RoomID\":" + roomID + "," +
                "\"Times\":{" +
                "\"Start\":" + startTime + "," +
                "\"End\":" + endTime + "}}";

        Publish(payload);

        ResponseType = CallbackResponseTypes.QueryReserveTimes;
    }

    public void MqttReserveRoom(int roomID, long startTime, long endTime){

        String payload = "{\"RequestType\":2," +
                "\"RoomID\":" + roomID + "," +
                "\"Times\":{" +
                "\"Start\":" + startTime + "," +
                "\"End\":" + endTime + "}}";

        Publish(payload);

        ResponseType = CallbackResponseTypes.ReserveRoom;
    }

    public void MqttAddParticipant(int reserveID, String participant){

        String payload = "{\"RequestType\":3," +
                "\"ReserveID\":" + reserveID + "," +
                "\"Participant\":\"" + participant + "\"}";

        Publish(payload);

        ResponseType = CallbackResponseTypes.AddParticipant;
    }
}

