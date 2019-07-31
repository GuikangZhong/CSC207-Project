package project.application;

import java.util.ArrayList;
import java.util.List;

public class ReferenceVerificationStrategy implements VerificationStrategy {
    private int referenceLettersRequired;

    @Override
    public boolean satisfies(Application application) {
        List<ReferenceLetter> letters = new ArrayList<>();
        for (Document document: application.getDocument()){
            if (document.getDocumentType().equals(ReferenceLetter.documentType())){
                letters.add((ReferenceLetter) document);
            }
        }
        if (letters.size() != referenceLettersRequired){
            return false;
        }
        return checkReferenceMatch(letters, application);
    }

    public void setReferenceLettersRequired(int numRequired){
        referenceLettersRequired = numRequired;
    }

    private boolean checkReferenceMatch(List<ReferenceLetter> letters, Application application){
        for (ReferenceLetter letter: letters){
            if (letter.getJobPosting() != application.getJob()){
                return false;
            }
        }
        return true;
    }
}
