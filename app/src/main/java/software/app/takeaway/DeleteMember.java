package software.app.takeaway;

import android.widget.Toast;

public class DeleteMember extends AccountSqlConnection {
    private String table = "member";
    private String username;

    public DeleteMember(String username) {
        this.username = username;

        connect();
    }

    public boolean delete() {
        try {
            statement = connection.createStatement();
            statement.execute("DELETE FROM " + table + " WHERE username='" + username + "'");
            Toast.makeText(MyContext.getContext(), "刪除成功", Toast.LENGTH_SHORT).show();
            return true;
        } catch (Exception e) {
            if (Debug.ENABLE)
                Toast.makeText(MyContext.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        } finally {
            disconnect();
        }
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
