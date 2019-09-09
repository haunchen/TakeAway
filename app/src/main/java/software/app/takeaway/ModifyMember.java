package software.app.takeaway;

import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ModifyMember extends AccountSqlConnection {
    private String table = "member";
    private String name, username, password, email;

    public ModifyMember(String username) {
        this("", username, "", "");
    }

    public ModifyMember(String name, String username, String password, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public boolean modify() {
        try {
            statement = connection.createStatement();
            statement.execute("UPDATE " + table + " SET name='" + this.name + "', password='" + this.password + "', email='" + this.email + "', modifyDate='" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()) + "' WHERE username='" + username + "'");

            return true;
        } catch (Exception e) {
            if (Debug.ENABLE)
                Toast.makeText(MyContext.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            disconnect();
        }
        return false;
    }

    public Account getUsernameData() {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + table);

            while (resultSet.next())
                if (resultSet.getString("username").equals(username))
                    return new Account(resultSet.getString("name"), resultSet.getString("username"), resultSet.getString("password"),
                            resultSet.getString("email"), resultSet.getBoolean("takeaway"), resultSet.getBoolean("administrator"));
        } catch (Exception e) {
            if (Debug.ENABLE)
                Toast.makeText(MyContext.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            disconnect();
        }
        return null;
    }
}
