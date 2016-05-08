package de.multithreaded.homecontrol.data;

/**
 * Created by dhelleberg on 08/05/16.
 */


public class Constants {
    enum Topics {
        TOPIC_OUTSIDE_TEMP("openHAB/state/Heating_Outside_Temperature_String/state");

        private final String topicPath;

        Topics(String topicPath) {
            this.topicPath = topicPath;
        }

        public String getTopicPath() {
            return topicPath;
        }
    }
}
