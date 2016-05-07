package de.multithreaded.homecontrol.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import javax.inject.Inject;

import de.multithreaded.homecontrol.HomeControlApplication;
import de.multithreaded.homecontrol.R;
import de.multithreaded.homecontrol.data.MQTTRXService;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Inject
    MQTTRXService mqttrxService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeControlApplication.get(this).getAppComponent().inject(this);


        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop ");
        mqttrxService.disconnect();
    }
}
