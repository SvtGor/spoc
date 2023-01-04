package pl.hirely.springboot.blog.model.service;

import org.springframework.stereotype.Service;
import pl.hirely.springboot.blog.model.domain.BlogPost;
import pl.hirely.springboot.blog.model.dto.BlogPostDto;
import pl.hirely.springboot.blog.model.dto.NewBlogPostDto;
import pl.hirely.springboot.blog.model.dto.NewCommentDto;
import pl.hirely.springboot.blog.model.mapper.BlogPostMapper;
import pl.hirely.springboot.blog.model.repository.BlogPostRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BlogService {

    private final BlogPostRepository blogPostRepository;
    private final BlogPostMapper blogPostMapper;
    private final TagService tagService;

    public BlogService(BlogPostRepository blogPostRepository, BlogPostMapper blogPostMapper, TagService tagService) {
        this.blogPostRepository = blogPostRepository;
        this.blogPostMapper = blogPostMapper;
        this.tagService = tagService;
    }

    public void addNewComment(Long postId, NewCommentDto newCommentDto) {
        blogPostRepository.findById(postId)
                .ifPresentOrElse(
                        post -> addNewComment(post, newCommentDto),
                        this::throwNotFoundException);
    }

    public BlogPostDto getPostById(Long postId) {
        return blogPostRepository.findById(postId)
                .map(blogPostMapper::mapBlogPostEntityToDto)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public List<BlogPostDto> getAllPosts() {
        return blogPostRepository.findAllWithComments()
                .stream()
                .map(blogPostMapper::mapBlogPostEntityToDto)
                .toList();
    }

    public void addNewPost(NewBlogPostDto newBlogPostDto) {
        BlogPost blogPost = blogPostMapper.mapNewBlogPostDtoToEntity(newBlogPostDto);
        tagService.findOrCreate(newBlogPostDto.getTags())
                .forEach(blogPost::addTag);
        blogPostRepository.save(blogPost);
    }

    public List<BlogPostDto> findAllByTagName(String tagName) {
        return blogPostRepository.findBlogPostByTagsName(tagName)
                .stream()
                .map(blogPostMapper::mapBlogPostEntityToDto)
                .collect(Collectors.toList());
    }

    private void addNewComment(BlogPost blogPost, NewCommentDto newCommentDto) {
        blogPost.addComment(blogPostMapper.mapBlogPostCommentDtoToEntity(newCommentDto));
    }

    private void throwNotFoundException() {
        throw new RuntimeException("Post not found");
    }
}
