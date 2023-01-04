package pl.hirely.springboot.booklibrary.model.dto;

import pl.hirely.springboot.booklibrary.model.domain.Book;

public class BookDto {
    private Long id;
    private String title;
    private String description;

    public static BookDto fromEntity(Book book) {
        return new BookDto(book.getId(), book.getTitle(), book.getDescription());
    }

    public static Book toEntity(BookDto book) {
        return new Book(book.getId(), book.getTitle(), book.getDescription());
    }

    // konstruktory, gettery, settery
    public BookDto(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
