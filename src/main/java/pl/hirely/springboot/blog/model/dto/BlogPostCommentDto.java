package pl.hirely.springboot.blog.model.dto;

import pl.hirely.springboot.blog.model.domain.BlogPostComment;

public class BlogPostCommentDto {

    private String content;

    public static BlogPostCommentDto fromEntity(BlogPostComment blogPostComment) {
        return new BlogPostCommentDto(blogPostComment.getContent());
    }

    public BlogPostCommentDto() {
    }

    public BlogPostCommentDto(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
