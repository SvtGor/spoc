package pl.hirely.springboot.booklibrary.model.service;

import org.springframework.stereotype.Service;
import pl.hirely.springboot.booklibrary.model.domain.Book;
import pl.hirely.springboot.booklibrary.model.dto.BookDto;
import pl.hirely.springboot.booklibrary.model.repository.BookRepository;
import pl.hirely.springboot.booklibrary.model.service.exception.NoBookException;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDto getBookById(Long id) {
        return bookRepository.findById(id)
                .map(book -> BookDto.fromEntity(book))
                .orElseThrow(() -> new NoBookException(String.format("Book %d is missing", id)));
    }

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(book -> BookDto.fromEntity(book))
                .collect(Collectors.toList());
    }

    public List<BookDto> getAllBooksSortBy(String field) {
        switch (field) {
            case "title":
                return getAllBooksSortBy(bookDto -> bookDto.getTitle());
            case "description":
                return getAllBooksSortBy(bookDto -> bookDto.getDescription());
            default:
                return getAllBooks();
        }
    }

    private List<BookDto> getAllBooksSortBy(Function<BookDto, String> sortOrder) {
        return bookRepository.findAll().stream()
                .map(book -> BookDto.fromEntity(book))
                .sorted(Comparator.comparing(sortOrder))
                .collect(Collectors.toList());
    }

    public BookDto createBook(BookDto bookDto) {
        Book newBook = BookDto.toEntity(bookDto);
        Book savedBook = bookRepository.save(newBook);
        return BookDto.fromEntity(savedBook);
    }

    public void editBook(BookDto bookDto, Long id) {
        bookRepository.findById(id)
                .map(book -> {
                    book.setDescription(bookDto.getDescription());
                    book.setTitle(bookDto.getTitle());
                    return book;
                })
                .ifPresent(book -> bookRepository.save(book));
    }

    public void deleteBook(Long id) {
        bookRepository.findById(id)
                .ifPresent(book -> bookRepository.delete(book));
    }
}
