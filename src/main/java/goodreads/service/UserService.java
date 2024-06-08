package goodreads.service;

import goodreads.model.User;
import java.util.HashMap;
import java.util.Map;

//singleton class
public class UserService {
    private static UserService userServiceInstance;
    private final Map<String, User> users;

    public static UserService getUserServiceSingleton(){
        if(userServiceInstance==null){
            userServiceInstance=new UserService();
        }
        return userServiceInstance;
    }

    private UserService(){
        this.users=new HashMap<>();
    }

    public Map<String, User> getUsers(){
        return users;
    }

    public User createUser(String username) {
        User user=new User(username);
        users.put(user.getUserId(),user);
        return user;
    }

    public User removeUser(String userId) {
        if (users.containsKey(userId))
            return users.remove(userId);
        return null;
    }

    public void addFriend(String userId, String friendId){
        users.get(userId).getFriends().add(friendId);
    }

    public void addBookToList(String userId, String bookId) {
        users.get(userId).getBooks().add(bookId);
    }
}






