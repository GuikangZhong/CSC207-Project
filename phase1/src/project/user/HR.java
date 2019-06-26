package project.user;

import project.application.Application;

import java.util.List;

public class HR extends User {
    HR(UserHistory history, String username, String password) {
        super(history, username, password);
    }

    @Override
    public Type getType() {
        return Type.HR;
    }

    // TODO: check with piazza / prof if this is really what it wants
    private List<List<Application>> recommandationLists;
}
