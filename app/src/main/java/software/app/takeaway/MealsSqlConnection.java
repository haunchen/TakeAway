package software.app.takeaway;

import android.os.StrictMode;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class MealsSqlConnection implements IpAddress {
    private static String db = "meals";
    protected static Connection connection = null;
    protected static Statement statement = null;
    protected static ResultSet resultSet = null;

    public static boolean connect() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + db + "?useUnicode=true&characterEncoding=big5", dbusername, dbpassword);
            return true;
        } catch (Exception e) {
            if (Debug.ENABLE)
                Toast.makeText(MyContext.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public static void disconnect() {
        try {
            if (statement != null) {
                statement.close();
                statement = null;
            }
            if (resultSet != null) {
                resultSet.close();
                resultSet = null;
            }
        } catch (Exception e) {
            if (Debug.ENABLE)
                Toast.makeText(MyContext.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
