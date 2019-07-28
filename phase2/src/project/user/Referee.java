package project.user;

import project.application.ReferenceLetter;
import project.observer.SystemObserver;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

public class Referee extends User implements Serializable, SystemObserver {
    private static final long serialVersionUID = -5182837267201535114L;
    private List<ReferenceLetter> letterList;

    public Referee(UserHistory history, String username, String password, String realName, List<String> company){
        super(history, username, password, realName, company);
        letterList = new ArrayList<>();
    }

    public void addLetter(ReferenceLetter letter) {
        letterList.add(letter);
    }

    public List<ReferenceLetter> getLetterList(){
        return letterList;
    }

    @Override
    public Type getType() {
        return Type.REFEREE;
    }

    @Override
    public void updateOnTime(LocalDateTime now) {

    }
}
