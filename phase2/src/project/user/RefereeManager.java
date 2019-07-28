package project.user;

import project.observer.SystemObserver;
import project.system.MainSystem;

import java.io.Serializable;
import java.time.LocalDateTime;

public class RefereeManager extends UserManager<Referee> implements SystemObserver, Serializable {
    private static final long serialVersionUID = -1515556997240183900L;

    public RefereeManager(MainSystem system) {
        super(system);
    }

    @Override
    public void updateOnTime(LocalDateTime now) {

    }
}
