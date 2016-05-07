package de.multithreaded.homecontrol;

import android.app.Application;
import android.content.Context;

/**
 * Created by dhelleberg on 23/01/16.
 */
public class HomeControlApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        setupComponent();
    }

    private void setupComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static HomeControlApplication get(Context context) {
        return (HomeControlApplication) context.getApplicationContext();
    }

}
