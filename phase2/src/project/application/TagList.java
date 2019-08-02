package project.application;

import java.io.Serializable;
import java.util.List;

public class TagList implements Serializable {
    private static TagList tagList;
    private static List<String> tags;
    private TagList(){}

    public static TagList getInstance(){
        if(tagList == null) tagList = new TagList();
        return tagList;
    }

    public static void addTag(String newTag){
        if(!tags.contains(newTag)) tags.add(newTag);
    }

    public static void removeTag(String oldTag){
        tags.remove(oldTag);
    }

    public static List<String> getTags(){
        return tags;
    }

}
