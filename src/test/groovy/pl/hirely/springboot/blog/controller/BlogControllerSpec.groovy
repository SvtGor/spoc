package pl.hirely.springboot.blog.controller

import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import pl.hirely.springboot.blog.model.dto.BlogPostCommentDto
import pl.hirely.springboot.blog.model.dto.BlogPostDto
import pl.hirely.springboot.blog.model.service.BlogService
import pl.hirely.springboot.blog.model.service.TagService
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = BlogController)
class BlogControllerSpec extends Specification {

    @Autowired
    private MockMvc mockMvc

    @SpringBean
    private BlogService blogService = Mock()

    @SpringBean
    private TagService tagService = Mock()

    def "should return 200 with correct body"() {
        given:
        blogService.getAllPosts() >> [
                new BlogPostDto("test-title", "test-content", []),
                new BlogPostDto("aa", "bb", [new BlogPostCommentDto("xxx")])
        ]

        when:
        def response = mockMvc.perform(get("/blog/post"))

        then:
        response
                .andExpect(status().isOk())
                .andExpect(jsonPath('$.length()').value(2))
                .andExpect(jsonPath('$[0].title').value("test-title"))
                .andExpect(jsonPath('$[0].content').value("test-content"))
                .andExpect(jsonPath('$[1].title').value("aa"))
                .andExpect(jsonPath('$[1].content').value("bb"))
                .andExpect(jsonPath('$[1].comments[0].content').value("xxx"))
    }
}
