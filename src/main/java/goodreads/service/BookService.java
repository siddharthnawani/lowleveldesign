package goodreads.service;


import goodreads.model.Book;
import java.util.HashMap;
import java.util.Map;

//singleton class
public class BookService {

    private static BookService bookServiceInstance;
    private final Map<String, Book> books;

    public static BookService getBookServiceSingleton() {
        if (bookServiceInstance == null)
            bookServiceInstance = new BookService();
        return bookServiceInstance;
    }

    private BookService() {
        this.books = new HashMap<>();
    }

    public Map<String, Book> getBooks() {
        return this.books;
    }

    public Book addBook(String bookName) {
        Book book = new Book(bookName);
        this.books.put(book.getBookId(), book);
        return book;
    }

}