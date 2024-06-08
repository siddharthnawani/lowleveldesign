package goodreads.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private String userId;
    private String name;
    private Set<String> friends;
    private  Set<String> books;

    public User(String name){
        this.name=name;
        this.userId= UUID.randomUUID().toString();
        this.friends=new HashSet<>();
        this.books=new HashSet<>();

    }
}
