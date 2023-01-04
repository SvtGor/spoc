package pl.hirely.springboot.blog.model.dto;

import java.util.List;

public class BlogPostDto {

    private String title;

    private String content;

    private List<BlogPostCommentDto> comments;

    public BlogPostDto(String title, String content, List<BlogPostCommentDto> comments) {
        this.title = title;
        this.content = content;
        this.comments = comments;
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

    public List<BlogPostCommentDto> getComments() {
        return comments;
    }

    public void setComments(List<BlogPostCommentDto> comments) {
        this.comments = comments;
    }
}
