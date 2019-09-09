package software.app.takeaway;

import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class GetMeals extends MealsSqlConnection {
    final private String table = "meals";

    public GetMeals() {
        connect();
    }

    public LinkedList<Meals> getData() {
        LinkedList<Meals> list = new LinkedList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + table);

            while(resultSet.next())
                list.add(new Meals(resultSet.getString("username"), resultSet.getString("name"), resultSet.getInt("price"), resultSet.getString("species")));

            connection.close();
            return list;
        } catch (Exception e) {
            if (Debug.ENABLE)
                Toast.makeText(MyContext.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            disconnect();
        }
        return null;
    }
}
