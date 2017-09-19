package lw.bookshare;

/**
 * Created by Adish on 19/09/17.
 */

public class userBooksAdd {

    String title;
    String author;
    public userBooksAdd(){
    }

    public userBooksAdd(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}

