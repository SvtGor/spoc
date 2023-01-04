package pl.hirely.springboot.blog.model.mapper;

import org.springframework.stereotype.Component;
import pl.hirely.springboot.blog.model.domain.BlogPost;
import pl.hirely.springboot.blog.model.domain.BlogPostComment;
import pl.hirely.springboot.blog.model.domain.Tag;
import pl.hirely.springboot.blog.model.dto.BlogPostCommentDto;
import pl.hirely.springboot.blog.model.dto.BlogPostDto;
import pl.hirely.springboot.blog.model.dto.NewBlogPostDto;
import pl.hirely.springboot.blog.model.dto.NewCommentDto;
import pl.hirely.springboot.blog.model.dto.TagPostDto;

import java.util.stream.Collectors;

@Component
public class BlogPostMapper {

    public BlogPost mapNewBlogPostDtoToEntity(NewBlogPostDto dto) {
        BlogPost blogPost = new BlogPost();
        blogPost.setTitle(dto.getTitle());
        blogPost.setContent(dto.getContent());
        return blogPost;
    }


    public BlogPostDto mapBlogPostEntityToDto(BlogPost blogPost) {
        return new BlogPostDto(blogPost.getTitle(), blogPost.getContent(), blogPost.getComments().stream()
                .map(BlogPostCommentDto::fromEntity)
                .toList());
    }

    public BlogPostComment mapBlogPostCommentDtoToEntity(NewCommentDto newCommentDto) {
        BlogPostComment blogPostComment = new BlogPostComment();
        blogPostComment.setContent(newCommentDto.getContent());
        return blogPostComment;
    }

    public TagPostDto mapTagEntityToTagPostDto(Tag tag) {
        return new TagPostDto(tag.getName(), tag.getPosts()
                .stream()
                .map(BlogPost::getTitle)
                .collect(Collectors.toList()));
    }
}
