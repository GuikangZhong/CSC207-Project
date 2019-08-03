package project.application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TagList implements Serializable {
    private  List<String> tags;

    public TagList(){tags = new ArrayList<>();
    }

    public  void addTag(String newTag){
        if(!tags.contains(newTag)) tags.add(newTag);
    }

    public  void removeTag(String oldTag){
        tags.remove(oldTag);
    }

    public  List<String> getTags(){
        return tags;
    }

}
