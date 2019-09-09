package software.app.takeaway;

import android.widget.Toast;

public class DeleteMeals extends MealsSqlConnection {
    private String table = "meals";
    private Meals meals;

    public DeleteMeals(Meals meals){
        this.meals = meals;
        connect();
    }

    public boolean delete(){
        try{
            statement = connection.createStatement();
            statement.execute("DELETE FROM " + table + " WHERE username='" + meals.getUsername() + "' and name='" + meals.getName() + "'");

            return true;
        }catch (Exception e){
            if(Debug.ENABLE) Toast.makeText(MyContext.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }finally {
            disconnect();
        }
        return false;
    }
}
