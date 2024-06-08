package goodreads.model;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Book {

    private String bookName;
    private String bookId;

    public Book(String bookName){
        this.bookName=bookName;
        this.bookId= UUID.randomUUID().toString();
    }

}
