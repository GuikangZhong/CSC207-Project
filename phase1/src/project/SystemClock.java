package project;

import java.io.Serializable;
import java.time.Clock;

// A reference to MainSystem's clock
// It's lifetime is as long as that of MainSystem
public class SystemClock implements Serializable {
    // obtain a clock
    // always call this method when a clock is needed
    // the returned clock has a very short lifetime
    // so never retain a reference to it
    public Clock getClock(){
        return Clock.systemDefaultZone();
    }
}
