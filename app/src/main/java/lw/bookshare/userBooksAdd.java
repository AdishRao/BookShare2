package lw.bookshare;

/**
 * Created by Adish on 19/09/17.
 */

public class userBooksAdd {

    String title;
    String author;
    String users;
    public userBooksAdd(){
    }

    public userBooksAdd(String title, String author, String users) {
        this.title = title;
        this.author = author;
        this.users = users;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getUsers() {
        return users;
    }
}

