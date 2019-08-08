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
        return letters.size() >= referenceLettersRequired;
    }

    public void setReferenceLettersRequired(int numRequired){
        referenceLettersRequired = numRequired;
    }
}
