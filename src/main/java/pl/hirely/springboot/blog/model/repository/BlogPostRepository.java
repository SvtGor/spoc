package pl.hirely.springboot.blog.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.hirely.springboot.blog.model.domain.BlogPost;

import java.util.List;
import java.util.Optional;

public interface BlogPostRepository extends JpaRepository<BlogPost,Long> {

    @Query("SELECT bp FROM BlogPost bp LEFT JOIN FETCH bp.comments WHERE bp.id = :postId")
    Optional<BlogPost> findWithCommentsById(Long postId);

    @Query("SELECT DISTINCT bp FROM BlogPost bp LEFT JOIN FETCH bp.comments")
    List<BlogPost> findAllWithComments();

//    List<BlogPost> findBlogPostByTagsName(String name);

    @Query("SELECT DISTINCT bp FROM BlogPost bp LEFT JOIN FETCH bp.comments " +
            "INNER JOIN FETCH bp.tags tgs WHERE tgs.name = :name")
    List<BlogPost> findBlogPostByTagsName(String name);
}
