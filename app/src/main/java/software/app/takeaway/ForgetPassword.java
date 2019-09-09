package software.app.takeaway;

import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ForgetPassword extends AccountSqlConnection {
    private String table = "member";
    private String username, password;

    public ForgetPassword(String username, String password) {
        this.username = username;
        this.password = password;

        connect();
    }

    public boolean update() {
        try {
            statement = connection.createStatement();
            statement.execute("UPDATE " + table + " SET password='" + password + "', modifyDate='" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()) + "' WHERE username='" + username + "'");
            Toast.makeText(MyContext.getContext(), "更改密碼成功", Toast.LENGTH_SHORT).show();
            return true;
        } catch (Exception e) {
            if (Debug.ENABLE)
                Toast.makeText(MyContext.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            disconnect();
        }
        return false;
    }

    public boolean searchData() {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + table);

            while (resultSet.next())
                if (resultSet.getString("username").equals(username))
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
