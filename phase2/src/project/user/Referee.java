package project.user;

import project.application.ReferenceLetter;

import java.io.Serializable;
import java.util.*;

public class Referee extends User implements Serializable {
    private static final long serialVersionUID = -5182837267201535114L;
    private List<ReferenceLetter> letterList;

    public Referee(UserHistory history, String username, String password, String realName, String company){
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
}
