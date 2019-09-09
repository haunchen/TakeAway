package software.app.takeaway;

import android.widget.Toast;

import java.util.LinkedList;

public class RestaurantData extends RestaurantSqlConnection {
    private String restaurant = "null";

    public RestaurantData() {
        connect();
    }

    public boolean setRestaurant(String restaurant) {
        try {
            statement = connection.createStatement();
            statement.execute("UPDATE recordrestaurant SET restaurant='" + restaurant + "' WHERE a='re'");
            return true;
        } catch (Exception e) {
            if (Debug.ENABLE)
                Toast.makeText(MyContext.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            disconnect();
        }
        return false;
    }

    public String getRestaurant() {
        return this.restaurant;
    }

    public boolean getRestaurantName() {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM recordrestaurant");
            resultSet.next();
            this.restaurant = resultSet.getString("restaurant");
            return true;
        } catch (Exception e) {
            if (Debug.ENABLE)
                Toast.makeText(MyContext.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            disconnect();
        }
        return false;
    }

    public LinkedList<Meals> getRestaurantData() {
        LinkedList<Meals> list = new LinkedList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + this.restaurant);

            while (resultSet.next())
                list.add(new Meals(resultSet.getString("name"), resultSet.getInt("price"), resultSet.getString("species")));

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
