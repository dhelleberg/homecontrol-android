package de.multithreaded.homecontrol.data;

import android.util.Log;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.Callback;
import org.fusesource.mqtt.client.CallbackConnection;
import org.fusesource.mqtt.client.ExtendedListener;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;

import java.net.URISyntaxException;

import de.multithreaded.homecontrol.BuildConfig;

/**
 * Created by dhelleberg on 07/05/16.
 */
public class MQTTRXService {

    private static final String TAG = "MQTTRXService";
    private final MQTT mqtt;
    private CallbackConnection connection;

    public MQTTRXService() {
        Log.d(TAG, "MQTTRXService ");
        this.mqtt = new MQTT();
        try {
            mqtt.setHost("tcp://"+BuildConfig.MQTT_HOST+":1883");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        connect();
    }

    public void disconnect() {
        Log.d(TAG, "disconnect ");
        connection.disconnect(new Callback<Void>() {
            @Override
            public void onSuccess(Void value) {
                Log.d(TAG, "onSuccess disconnect");
            }

            @Override
            public void onFailure(Throwable value) {

            }
        });
    }

    public void connect() {
        Log.d(TAG, "connect ");
        connection = mqtt.callbackConnection();
        connection.listener(new ExtendedListener() {
            @Override
            public void onPublish(UTF8Buffer topic, Buffer body, Callback<Callback<Void>> ack) {
                Log.d(TAG, "onPublish topic: "+topic);

            }

            @Override
            public void onConnected() {
                Log.d(TAG, "onConnected ");
            }

            @Override
            public void onDisconnected() {
                Log.d(TAG, "onDisconnected ");
            }

            @Override
            public void onPublish(UTF8Buffer topic, Buffer body, Runnable ack) {
                Log.d(TAG, "onRPublish topic: "+topic);
                ack.run();
            }

            @Override
            public void onFailure(Throwable value) {
                Log.d(TAG, "onFailure ");
            }
        });
        connection.connect(new Callback<Void>() {
            @Override
            public void onSuccess(Void value) {
                // Subscribe to a topic
                Topic[] topics = {new Topic("#", QoS.AT_LEAST_ONCE)};
                connection.subscribe(topics, new Callback<byte[]>() {

                    @Override
                    public void onSuccess(byte[] value) {
                        Log.d(TAG, "onSuccess subsribe");
                    }

                    @Override
                    public void onFailure(Throwable value) {
                        Log.d(TAG, "onFailure subscribe");
                    }
                });
            }

            @Override
            public void onFailure(Throwable value) {
                Log.d(TAG, "onFailure Connect", value   );
            }
        });
    }
}
