package goodreads.strategy;

import goodreads.model.Book;
import goodreads.model.User;
import goodreads.util.Pair;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;


public class BookLookupStrategyImpl implements BookLookupStrategy{

    @Override
    public List<Book> getTopUserBooksThatFriendsHaveRead(Map<String, Book> books, Map<String, User> users, String userId, int capacity) {
        User user = users.get(userId);

        Map<String, Integer> popularityCounter = user.getBooks()
            .stream()
            .flatMap(
                book -> user.getFriends()
                    .stream()
                    .filter(friend -> users.get(friend).getBooks().contains(book))
                    .map(friend -> book)
            ).collect(Collectors.toMap(
                book -> book,
                book -> 1,
                Integer::sum
            ));

        return popularityCounter.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue())
            .limit(capacity)
            .map(entry -> books.get(entry.getKey()))
            .collect(Collectors.toList());

    }

    @Override
    public List<Book> getTopBooksThatNetworksHaveRead(Map<String, Book> books, Map<String, User> users, String userId, int capacity, int maxDepth) {
        Set<String> visited = new HashSet<>();
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        Map<String, Integer> bookFrequency = new HashMap<>();
        queue.add(new Pair<>(userId, 0));
        visited.add(userId);
        while (!queue.isEmpty()) {
            Pair<String, Integer> currentPair = queue.poll();
            String currentUserId = currentPair.getFirst();
            if(currentPair.getSecond() == maxDepth)
                break;
            User currentUser = users.get(currentUserId);

            currentUser.getBooks().forEach(book -> {
                bookFrequency.put(book, bookFrequency.getOrDefault(book, 0)+1);
            });

            currentUser.getFriends().forEach(friend -> {
                if(!visited.contains(friend)) {
                    visited.add(friend);
                    queue.add(new Pair<>(friend, currentPair.getSecond()+1));
                }
            });
        }
        return bookFrequency.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue())
            .limit(capacity)
            .map(book -> books.get(book.getKey()))
            .collect(Collectors.toList());
    }
}
