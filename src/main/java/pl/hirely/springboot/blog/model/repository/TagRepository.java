package pl.hirely.springboot.blog.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.hirely.springboot.blog.model.domain.Tag;

import java.util.List;
import java.util.Set;

public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query("SELECT DISTINCT t FROM Tag t JOIN FETCH t.posts")
    List<Tag> findTagsWithPosts();

    Set<Tag> findAllByNameIn(Set<String> name);
}