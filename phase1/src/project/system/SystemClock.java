package project.system;

import java.io.Serializable;
import java.time.Clock;
import java.time.Duration;

// A reference to MainSystem's clock
// It's lifetime is as long as that of MainSystem
public class SystemClock implements Serializable {
    private Clock clock;

    // obtain a clock
    // always call this method when a clock is needed
    // the returned clock has a very short lifetime
    // so never retain a reference to it
    public Clock getClock(){
        return clock;
    }


    void offset(Duration duration){
        clock = Clock.offset(clock, duration);
    }
}
