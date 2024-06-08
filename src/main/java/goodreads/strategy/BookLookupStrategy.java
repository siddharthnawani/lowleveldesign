package goodreads.strategy;

import goodreads.model.Book;
import goodreads.model.User;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface BookLookupStrategy {

    List<Book> getTopUserBooksThatFriendsHaveRead(Map<String, Book> books, Map<String, User> users, String userId, int capacity);

    List<Book> getTopBooksThatNetworksHaveRead(Map<String, Book> books, Map<String, User> users, String userId, int capacity, int maxDepth);

    default List<Book> getBooks(Map<String, Book> books, Map<String, User> users, String userId){
        return users.get(userId).getBooks().stream().map(books::get).collect(Collectors.toList());
    }

}
