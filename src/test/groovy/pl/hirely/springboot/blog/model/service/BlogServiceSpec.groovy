package pl.hirely.springboot.blog.model.service


import pl.hirely.springboot.blog.model.domain.BlogPost
import pl.hirely.springboot.blog.model.domain.BlogPostComment
import pl.hirely.springboot.blog.model.domain.Tag
import pl.hirely.springboot.blog.model.dto.BlogPostDto
import pl.hirely.springboot.blog.model.dto.NewBlogPostDto
import pl.hirely.springboot.blog.model.dto.NewCommentDto
import pl.hirely.springboot.blog.model.mapper.BlogPostMapper
import pl.hirely.springboot.blog.model.repository.BlogPostRepository
import spock.lang.Specification
import spock.lang.Subject

class BlogServiceSpec extends Specification {

    private BlogPostRepository repository = Mock()
    private BlogPostMapper mapper = Mock()
    private TagService tagService = Mock()

    @Subject
    private BlogService blogService = new BlogService(repository, mapper, tagService)

    def "should return proper DTO"() {
        given:
        def postId = 1L
        def postEntity = new BlogPost(title: "my-title-1", content: "my-content-1")

        repository.findById(postId) >> Optional.of(postEntity)
        mapper.mapBlogPostEntityToDto(postEntity) >> new BlogPostDto("my-title-1", "my-content-1", [])

        when:
        def actual = blogService.getPostById(postId)

        then:
        actual.title == "my-title-1"
        actual.content == "my-content-1"
    }

    def "should throw exception when blogpost with given id does not exist"() {
        given:
        repository.findById(1L) >> Optional.empty()

        when:
        blogService.getPostById(1L)

        then:
        def e = thrown(RuntimeException)
        e.message == "Post not found"
    }

    def "should save new post"() {
        given: "a blog post dto"
        def dto = new NewBlogPostDto("test-title", "test-content", Set.of("t1", "t2"))
        def postEntity = new BlogPost(title: "test-title", content: "test-content")

        tagService.findOrCreate(["t1", "t2"] as Set) >> ([new Tag(name: "t1"), new Tag(name: "t2")] as Set)

        when: "save is called"
        blogService.addNewPost(dto)

        then: "save should be executed"
        1 * repository.save({BlogPost bp ->
            bp.title == "test-title"
            bp.content == "test-content"
            bp.tags*.name as Set == ["t1", "t2"] as Set
        })

        and: "mapper should be used"
        1 * mapper.mapNewBlogPostDtoToEntity({NewBlogPostDto newDto ->
            dto.title == "test-title"
            dto.content == "test-content"
            dto.tags == ["t1", "t2"] as Set
        }) >> postEntity
    }

    def "should add comment to post entity"() {
        given:
        Long postId = 1L
        def commentDto = new NewCommentDto("test-comment-content")
        def postEntity = new BlogPost(title: "t", content: "c", comments: [])

        repository.findById(postId) >> Optional.of(postEntity)
        mapper.mapBlogPostCommentDtoToEntity(commentDto) >> new BlogPostComment(content: "test-comment-content")

        when:
        blogService.addNewComment(postId, commentDto)

        then:
        postEntity.comments.size() == 1
        postEntity.comments*.content as Set == ["test-comment-content"] as Set
    }
}
