package software.app.takeaway;

import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SetTakeaway extends AccountSqlConnection {
    private String table = "member";
    private String username;
    private boolean takeaway;

    public SetTakeaway(String username, boolean takeaway) {
        this.username = username;
        this.takeaway = takeaway;

        connect();
    }

    public boolean update() {
        try {
            statement = connection.createStatement();
            statement.execute("UPDATE " + table + " SET takeaway='" + (takeaway ? 1 : 0) + "', modifyDate='" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()) + "' WHERE username='" + username + "'");
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
