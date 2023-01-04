package pl.hirely.springboot.booklibrary.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import pl.hirely.springboot.booklibrary.model.dto.BookDto;
import pl.hirely.springboot.booklibrary.model.service.BookService;
import pl.hirely.springboot.booklibrary.model.service.exception.NoBookException;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<BookDto> getBooks() {
        return bookService.getAllBooks();
    }

    @RequestMapping(value = "/sorted", method = RequestMethod.GET)
    public List<BookDto> getBooks(@RequestParam(name = "property") String sortProperty) {
        return bookService.getAllBooksSortBy(sortProperty);
    }

    @RequestMapping(method = RequestMethod.POST)
    public BookDto createBook(@RequestBody BookDto bookDto) {
        return bookService.createBook(bookDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BookDto getBook(@PathVariable("id") Long id) {
        return bookService.getBookById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void editBook(@RequestBody BookDto bookDto, @PathVariable("id") Long id) {
        bookService.editBook(bookDto, id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }

    @ControllerAdvice
    public class RestResponseEntityExceptionHandler {

        @ExceptionHandler(value = NoBookException.class)
        protected ResponseEntity<MissingBookResponse> handleNoBook(NoBookException ex, WebRequest request) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MissingBookResponse(ex.getMessage()));
        }
    }

    private class MissingBookResponse {
        private final String errorMessage;

        public MissingBookResponse(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }
}
