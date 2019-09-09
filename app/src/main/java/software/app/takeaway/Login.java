package software.app.takeaway;

import android.widget.Toast;

public class Login extends AccountSqlConnection implements IpAddress {
    private String table = "member";
    private String username, password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;

        connect();
    }

    public boolean isMember() {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + table);

            while (resultSet.next())
                if (resultSet.getString("username").equals(username) && resultSet.getString("password").equals(password))
                    return true;
        } catch (Exception e) {
            if (Debug.ENABLE)
                Toast.makeText(MyContext.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            disconnect();
        }
        return false;
    }

    public boolean isAdministrator() {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + table);

            while (resultSet.next())
                if (resultSet.getString("username").equals(username) && resultSet.getBoolean("administrator"))
                    return true;
        } catch (Exception e) {
            if (Debug.ENABLE)
                Toast.makeText(MyContext.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            disconnect();
        }
        return false;
    }

    public boolean isTakeaway() {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + table);

            while (resultSet.next())
                if (resultSet.getString("username").equals(username) && resultSet.getBoolean("takeaway"))
                    return true;
        } catch (Exception e) {
            if (Debug.ENABLE)
                Toast.makeText(MyContext.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            disconnect();
        }
        return false;
    }
}
