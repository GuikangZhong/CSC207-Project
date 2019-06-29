package project.user;

import java.util.Collections;
import java.util.Collection;
import java.util.Optional;

public interface HRSelectionStrategy {
    Optional<HR> select(Collection<HR> hrList);
}
