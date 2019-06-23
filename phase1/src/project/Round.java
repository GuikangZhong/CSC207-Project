package project;

import java.util.Collection;

public class Round {

    public int getTime() {
        return time;
    }

    private final int time;

    public ApplicantPool getPool() {
        return pool;
    }

    private ApplicantPool pool;

    Round(int time, ApplicantPool pool){
        this.time = time;
        this.pool = pool;
    }


}
