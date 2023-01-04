package pl.hirely.springboot.booklibrary.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.hirely.springboot.booklibrary.model.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
