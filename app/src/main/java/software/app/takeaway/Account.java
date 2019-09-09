package software.app.takeaway;

public class Account {
    private String name, username, password, email;
    private boolean takeaway, administrator;

    public Account(String name, String username, String password, String email, boolean takeaway, boolean administrator) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.takeaway = takeaway;
        this.administrator = administrator;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public boolean getTakeaway() {
        return this.takeaway;
    }

    public boolean getAdministrator() {
        return this.administrator;
    }

    public String toString() {
        return name + " " + username + " " + password + " " + email;
    }
}
