package pl.hirely.springboot.blog.model.dto;

import java.util.HashSet;
import java.util.Set;

public class NewBlogPostDto {

    private String title;

    private String content;

    private Set<String> tags = new HashSet<>();

    public NewBlogPostDto(String title, String content, Set<String> tags) {
        this.title = title;
        this.content = content;
        this.tags = tags;
    }

    public NewBlogPostDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public NewBlogPostDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}
