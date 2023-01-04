package pl.hirely.springboot.blog.model.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import pl.hirely.springboot.blog.model.domain.BlogPost
import spock.lang.Specification
import spock.lang.Subject

@DataJpaTest
class BlogPostRepositorySpec extends Specification {

    @Autowired
    @Subject
    private  BlogPostRepository blogPostRepository

    def "should find post by id with comments"() {
        when:
        def actual = blogPostRepository.findWithCommentsById(2L)

        then:
//       tak kak mamy optional, to spoczontku sprawdzi czy on jest  za pomocy isPresent()
        actual.isPresent()

        actual.get().title=='title-2'
        actual.get().content=='content-2'

//        tak kak każdy comment ma swój content, to *.   comments*.content
        actual.get().comments*.content as Set==['comment-2','comment-3']as Set
    }
}
