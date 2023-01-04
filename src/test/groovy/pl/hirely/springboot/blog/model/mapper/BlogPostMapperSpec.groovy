package pl.hirely.springboot.blog.model.mapper

import pl.hirely.springboot.blog.model.domain.BlogPost
import pl.hirely.springboot.blog.model.domain.BlogPostComment
import pl.hirely.springboot.blog.model.domain.Tag
import pl.hirely.springboot.blog.model.dto.NewBlogPostDto
import pl.hirely.springboot.blog.model.dto.NewCommentDto
import spock.lang.Specification
import spock.lang.Subject

class BlogPostMapperSpec extends Specification {

    @Subject
    private BlogPostMapper mapper = new BlogPostMapper()

    def "should map new blog post DTO to Entity"() {
        given:
        NewBlogPostDto newDto = new NewBlogPostDto("test-title",
                "test-content",
                Set.of("tag1", "tag2"))

        when:
        def actual = mapper.mapNewBlogPostDtoToEntity(newDto)

        then:
        actual.title == "test-title"
        actual.content == "test-content"
    }

    def "should map blog post entity to DTO"() {
        given:
        def blogPost = new BlogPost(title: "test-title", content: "test-content", comments: [
                new BlogPostComment(content: "test-comment-1"),
                new BlogPostComment(content: "test-comment-2")
        ])

        when:
        def actual = mapper.mapBlogPostEntityToDto(blogPost)

        then:
        actual.title == "test-title"
        actual.content == "test-content"
        actual.comments*.content == ["test-comment-1", "test-comment-2"]
    }

    def "should map comment DTO to entity"() {
        given:
        NewCommentDto dto = new NewCommentDto("test-content")

        when:
        def actual = mapper.mapBlogPostCommentDtoToEntity(dto)

        then:
        actual.content == "test-content"
    }

    def "should map tag entity ty DTO"() {
        given:
        Tag tag = new Tag(name: "test-name",
                posts: [ new BlogPost(id: 1L, title: "t1"), new BlogPost(id: 2L, title: "t2") ] as Set)

        when:
        def actual = mapper.mapTagEntityToTagPostDto(tag)

        then:
        actual.tagName == "test-name"
        actual.postTitles as Set == ["t1", "t2"] as Set
    }
}
