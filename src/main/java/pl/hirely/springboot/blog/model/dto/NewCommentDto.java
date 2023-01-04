package pl.hirely.springboot.blog.model.dto;

public class NewCommentDto {

    private String content;

    public NewCommentDto() {
    }

    public NewCommentDto(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
