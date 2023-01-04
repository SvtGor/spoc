package pl.hirely.springboot.blog.model.dto;

import java.util.List;

public class TagPostDto {

    private String tagName;

    private List<String> postTitles;

    public TagPostDto() {
    }

    public TagPostDto(String tagName, List<String> postTitles) {
        this.tagName = tagName;
        this.postTitles = postTitles;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<String> getPostTitles() {
        return postTitles;
    }

    public void setPostTitles(List<String> postTitles) {
        this.postTitles = postTitles;
    }
}
