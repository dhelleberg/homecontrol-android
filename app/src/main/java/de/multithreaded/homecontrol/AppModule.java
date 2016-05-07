package de.multithreaded.homecontrol;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.multithreaded.homecontrol.data.MQTTRXService;

/**
 * Created by dhelleberg on 23/01/16.
 */
@Module
public class AppModule {
    private final HomeControlApplication homeControlApplication;

    public AppModule(HomeControlApplication homeControlApplication) {
        this.homeControlApplication = homeControlApplication;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return homeControlApplication;
    }

    @Provides
    @Singleton
    public MQTTRXService provideMQTTRXService() {
        return new MQTTRXService();
    }


}
