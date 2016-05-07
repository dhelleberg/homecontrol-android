package de.multithreaded.homecontrol.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import de.multithreaded.homecontrol.HomeControlApplication;
import de.multithreaded.homecontrol.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeControlApplication.get(this).getAppComponent().inject(this);


        setContentView(R.layout.activity_main);
    }
}
