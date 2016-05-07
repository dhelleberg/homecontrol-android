package de.multithreaded.homecontrol;

import javax.inject.Singleton;

import dagger.Component;
import de.multithreaded.homecontrol.ui.MainActivity;

/**
 * Created by dhelleberg on 23/01/16.
 */
@Singleton
@Component ( modules = {AppModule.class})
public interface AppComponent {
    void inject(HomeControlApplication application);
    void inject(MainActivity uiComponent);
}
