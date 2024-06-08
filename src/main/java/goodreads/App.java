package goodreads;

import goodreads.facade.GoodReadsFacade;
import goodreads.model.Book;
import goodreads.model.User;

public class App {
    public static void main(String[] args) {
        GoodReadsFacade alexa = new GoodReadsFacade();
        alexa.initialize();
        User ben = alexa.createNewUser("Ben");
        User ram = alexa.createNewUser("Ram");
        User mohan = alexa.createNewUser("Mohan");
        User kevin = alexa.createNewUser("Kevin");
        Book book1 = alexa.addNewBook("Book1");
        Book book2 = alexa.addNewBook("Book2");
        Book book3 = alexa.addNewBook("Book3");
        Book book4 = alexa.addNewBook("Book4");
        Book book5 = alexa.addNewBook("Book5");
        Book book6 = alexa.addNewBook("Book6");

        alexa.addBookToList(ben.getUserId(), book1.getBookId());
        alexa.addBookToList(ben.getUserId(), book2.getBookId());
        alexa.addBookToList(ben.getUserId(), book3.getBookId());

        alexa.addBookToList(ram.getUserId(), book3.getBookId());
        alexa.addBookToList(ram.getUserId(), book4.getBookId());
        alexa.addBookToList(ram.getUserId(), book5.getBookId());

        alexa.addBookToList(mohan.getUserId(), book1.getBookId());
        alexa.addBookToList(mohan.getUserId(), book2.getBookId());
        alexa.addBookToList(mohan.getUserId(), book6.getBookId());

        alexa.addBookToList(kevin.getUserId(), book5.getBookId());
        alexa.addBookToList(kevin.getUserId(), book1.getBookId());
        alexa.addBookToList(kevin.getUserId(), book4.getBookId());

        alexa.addFriend(kevin.getUserId(), ram.getUserId());
        alexa.addFriend(ben.getUserId(), mohan.getUserId());
        alexa.addFriend(ram.getUserId(), ben.getUserId());

        System.out.println("Kevin's all books :");
        alexa.getUserBooks(kevin.getUserId()).forEach(System.out::println); //fetch all user books
        System.out.println("Popular books that are in Kevin's list :");
        alexa.getTopUserBooksThatFriendsHaveRead(kevin.getUserId(), 2).forEach(System.out::println); //fetch popular books that are in Kevin's list
        System.out.println("Popular books in the network :");
        alexa.getTopBooksThatNetworksHaveRead(kevin.getUserId(), 3, 3).forEach(System.out::println); //fetch popular books from a graph of network up to a certain depth
    }
}
