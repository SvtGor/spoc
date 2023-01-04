package pl.hirely.springboot.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.hirely.springboot.blog.model.dto.BlogPostDto;
import pl.hirely.springboot.blog.model.dto.NewBlogPostDto;
import pl.hirely.springboot.blog.model.dto.NewCommentDto;
import pl.hirely.springboot.blog.model.dto.TagPostDto;
import pl.hirely.springboot.blog.model.service.BlogService;
import pl.hirely.springboot.blog.model.service.TagService;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;
    private final TagService tagService;

    public BlogController(BlogService blogService, TagService tagService) {
        this.blogService = blogService;
        this.tagService = tagService;
    }

    @PostMapping("/post")
    public void addNewPost(@RequestBody NewBlogPostDto newBlogPostDto) {
        blogService.addNewPost(newBlogPostDto);
    }

    @GetMapping("/post")
    public List<BlogPostDto> getAllPosts() {
        return blogService.getAllPosts();
    }

    @GetMapping("/post/{postId}")
    public BlogPostDto getPostById(@PathVariable("postId") Long postId) {
        return blogService.getPostById(postId);
    }

    @PostMapping("/post/{postId}/comment")
    @Transactional
    public void addNewComment(@PathVariable("postId") Long postId, @RequestBody NewCommentDto newCommentDto) {
        blogService.addNewComment(postId, newCommentDto);
    }

    @GetMapping("/post/tag/{tagName}")
    public List<BlogPostDto> getPostsByTag(@PathVariable("tagName") String tagName) {
        return blogService.findAllByTagName(tagName);
    }

    @GetMapping("/tag/post")
    public List<TagPostDto> getPostsByTag() {
        return tagService.findAllTagsWithPosts();
    }
}