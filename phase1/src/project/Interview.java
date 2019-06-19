package project;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Interview implements Serializable {
    public Collection<Interviewee> getInterviewees() {
        return Collections.unmodifiableCollection(interviewees);
    }

    private Collection<Interviewee> interviewees;
    private Interviewer interviewer;

    public List<Interviewee> getInterviewersPassed() {
        return Collections.unmodifiableList(
                interviewees.stream().filter(interviewee -> interviewee.isPassed())
                        .collect(Collectors.toList()));
    }
}
