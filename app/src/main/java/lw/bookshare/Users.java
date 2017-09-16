package lw.bookshare;

/**
 * Created by Adish on 16/09/17.
 */

public class Users {
    String username;
    String phone;
    String location;

    public Users(){

    }

    public Users( String username, String phone, String location) {
        this.username = username;
        this.phone = phone;
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public String getLocation() {
        return location;
    }
}
