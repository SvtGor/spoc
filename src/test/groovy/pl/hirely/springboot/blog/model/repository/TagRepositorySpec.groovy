package pl.hirely.springboot.blog.model.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import pl.hirely.springboot.blog.model.domain.Tag
import spock.lang.Specification
import spock.lang.Subject

@DataJpaTest

class TagRepositorySpec extends Specification {
    @Subject
    @Autowired
    private TagRepository tagRepository

    def "should find tags in database"(){
        when:
        def actual = tagRepository.findAllByNameIn(["t1","t3","t4"] as Set)

        then:
       actual*.name as Set == ["t1"] as Set

    }
}
