package software.app.takeaway;

import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddMeals extends MealsSqlConnection {
    private String table = "meals";
    private Meals meals;

    public AddMeals(){
        connect();
    }

    public AddMeals(Meals meals){
        this.meals = meals;

        connect();
    }

    public boolean add(){
        try{
            statement = connection.createStatement();
            statement.execute("INSERT INTO " + table + "(username, name, price, species, date) VALUES ('"
                    + meals.getUsername() + "','" + meals.getName() + "','" + meals.getPrice() + "','" + meals.getSpecies() + "','" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()) + "')");

            return true;
        }catch (Exception e){
            if(Debug.ENABLE) Toast.makeText(MyContext.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }finally {
            disconnect();
        }
        return false;
    }

    public boolean delete(){
        try{
            statement = connection.createStatement();
            statement.execute("truncate table " + table);

            return true;
        }catch (Exception e){
            if(Debug.ENABLE) Toast.makeText(MyContext.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }finally {
            disconnect();
        }
        return false;
    }
}
