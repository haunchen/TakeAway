package software.app.takeaway;

import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Register extends AccountSqlConnection {
    private String table = "member";
    private String name, username, password, email;

    public Register(String name, String username, String password, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;

        connect();
    }

    public boolean insert() {
        try {
            if (!searchData()) {
                String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
                statement = connection.createStatement();
                statement.execute("INSERT INTO " + table
                        + "(name, username, password, email, establishmentDate, modifyDate, takeaway, administrator) VALUES ('"
                        + this.name + "','" + this.username + "','" + this.password + "','" + this.email + "','"
                        + date + "','" + date + "','" + 0 + "','" + 0 + "')");
                Toast.makeText(MyContext.getContext(), "註冊成功", Toast.LENGTH_SHORT).show();
                return true;
            } else
                Toast.makeText(MyContext.getContext(), "帳號已存在", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            if (Debug.ENABLE)
                Toast.makeText(MyContext.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            disconnect();
        }
        return false;
    }

    private boolean searchData() {
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
