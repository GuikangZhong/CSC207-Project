package project.application;

import project.interview.InterviewSetup;
import project.observer.SystemObserver;
import project.system.MainSystem;
import project.user.*;
import project.utils.Logging;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;

public class Company implements Serializable, SystemObserver, Iterable<Company> {
    private static final long serialVersionUID = 2088083308860080279L;
    private static Logger logger = Logging.getLogger();
    private String name;
    private JobPostingManager jobPostingManager;
    private UserManager<Interviewer> interviewerManager;
    private HRManager hrManager;
    private MainSystem system;
    private HashMap<String, InterviewSetup> interviewFormats;
    private List<Company> subsidiaries;
    private Company parentCompany;
    private List<String> tags;


    public Company(String name, MainSystem system) {
        this.name = name;
        this.system = system;
        jobPostingManager = new JobPostingManager(this);
        hrManager = new HRManager(system);
        interviewerManager = new UserManager<>(system);
        interviewFormats = new HashMap<>();
        subsidiaries = new ArrayList<>();
        parentCompany = null;
        tags = new ArrayList<>(Arrays.asList(
                "Full-time",
                "Internship",
                "Management",
                "Remote"
        ));
    }

    public InterviewSetup getInterviewFormat(String formatName) {
        return interviewFormats.get(formatName);
    }

    public Set<String> getFormatNames() {
        return Collections.unmodifiableSet(interviewFormats.keySet());
    }

    public void addInterviewFormat(String formatName, InterviewSetup setup) {

        if (interviewFormats.keySet().contains(formatName)) {
            throw new RuntimeException();
        }
        for (InterviewSetup format : interviewFormats.values()) {
            if (format.equalSetup(setup)) {
                throw new RuntimeException();
            }
        }
        interviewFormats.put(formatName, setup);
    }


    public MainSystem getSystem() {
        return system;
    }

    public String getName() {
        return name;
    }

    public JobPostingManager getJobPostingManager() {
        return jobPostingManager;
    }

    public HRManager getHrManager() {
        return hrManager;
    }

    public UserManager<Interviewer> getInterviewerManager() {
        return interviewerManager;
    }

    public boolean addUser(User user) {
        if (user.getType() == User.Type.HR) {
            return hrManager.addUser((HR) user);
        } else if (user.getType() == User.Type.INTERVIEWER) {
            return interviewerManager.addUser((Interviewer) user);
        }
        return false;
    }

    public User getUser(String username) {
        if (hrManager.containsUser(username)) {
            return hrManager.getUser(username);
        } else if (interviewerManager.containsUser(username)) {
            return interviewerManager.getUser(username);
        }
        return null;
    }

    public List<String> getTags() {
        return tags;
    }

    public boolean addTag(String newTag) {
        if (!tags.contains(newTag)) {
            tags.add(newTag);
            return true;
        }
        return false;
    }

    public void removeTag(String oldTag) {
        this.tags.remove(oldTag);
    }

    public void addSubsidiary(Company subsidiary) {
        subsidiaries.add(subsidiary);
    }

    public Company getParentCompany() {
        return parentCompany;
    }

    public void setParentCompany(Company parent) {
        if (parentCompany == null)
            parentCompany = parent;
        else
            throw new RuntimeException("You cannot set parent for the second time");
    }

    public List<Company> getSubsidiaries() {
        return subsidiaries;
    }

    @Override
    public void updateOnTime(LocalDateTime now) {
        logger.info("Update on " + now);
        jobPostingManager.updateOnTime(now);
    }

    /**
     *
     * @param company: The company of which we will get all the subsidiaries.
     * @return: All the companies that are directly the subsidiary of company or the subsidiary of
     * a subsidiary of company,... and so on.
     */
    private static Deque<Company> retrieveAllSubsidiaries(Company company) {
        Deque<Company> result = new LinkedList<>();
        Deque<Company> queue = new LinkedList<>();
        queue.addLast(company);
        while (!queue.isEmpty()) {
            Company c = queue.removeFirst();
            if (c != company)
                result.addLast(c);
            for (Company sub : c.getSubsidiaries()) {
                queue.addLast(sub);
            }
        }
        return result;
    }

    private class Iter implements Iterator<Company> {
        Deque<Company> queue;
        Iterator<Company> iterator;

        Iter(Company company) {
            queue = retrieveAllSubsidiaries(company);
            iterator = queue.iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Company next() {
            return iterator.next();
        }
    }

    @Override
    public Iterator<Company> iterator() {
        return new Iter(this);
    }
}
