package goodreads.facade;


import goodreads.model.Book;
import goodreads.model.User;
import goodreads.service.BookService;
import goodreads.service.UserService;
import goodreads.strategy.BookLookupStrategy;
import goodreads.strategy.BookLookupStrategyImpl;
import java.util.List;

public class GoodReadsFacade {

    private UserService userService;
    private BookService bookService;
    private BookLookupStrategy bookLookupStrategy;

    public void initialize() {
        this.userService = UserService.getUserServiceSingleton();
        this.bookService = BookService.getBookServiceSingleton();
        this.bookLookupStrategy = new BookLookupStrategyImpl();
    }

    public User createNewUser(String userName) {
        return this.userService.createUser(userName);
    }

    public void addFriend(String user1, String user2) {
        this.userService.addFriend(user1, user2);
        this.userService.addFriend(user2, user1);
    }

    public void addBookToList(String userId, String bookId) {
        this.userService.addBookToList(userId, bookId);
    }

    public List<Book> getUserBooks(String userId) {
        return this.bookLookupStrategy.getBooks(bookService.getBooks(), userService.getUsers(), userId);
    }

    public Book addNewBook(String bookName) {
        return this.bookService.addBook(bookName);
    }

    public List<Book> getTopUserBooksThatFriendsHaveRead(String userId, int capacity) {
        return this.bookLookupStrategy.getTopUserBooksThatFriendsHaveRead(bookService.getBooks(), userService.getUsers(), userId, capacity);
    }

    public List<Book> getTopBooksThatNetworksHaveRead(String userId, int capacity, int depth) {
        return this.bookLookupStrategy.getTopBooksThatNetworksHaveRead(bookService.getBooks(), userService.getUsers(), userId, capacity, depth);
    }
}