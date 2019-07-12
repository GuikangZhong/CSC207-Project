package project.user;

import project.application.JobPosting;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

public interface HRSelectionStrategy extends Serializable {
    Optional<HR> select(JobPosting jobPosting, Collection<HR> hrList);
}
