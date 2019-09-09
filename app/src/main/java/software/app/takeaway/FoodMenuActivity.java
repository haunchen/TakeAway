package software.app.takeaway;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;


public class FoodMenuActivity extends Fragment {

    private LinearLayout display;
    private HashMap<String, Meals> foodKey;
    private LinkedList<Meals> allFood;
    private String oldSpecies = "";

    private static final String ARG_PARAM1 = "param2";
    private String mParam;

    public static FoodMenuActivity newInstance(String param) {
        FoodMenuActivity fragment = new FoodMenuActivity();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param);
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_food_meun, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MyContext.setContext(getContext());

        display = getView().findViewById(R.id.display);
        display.setBackgroundColor(0xFFC6C6C6);
        foodKey = new HashMap<>();
        RestaurantData restaurantData = new RestaurantData();
        restaurantData.getRestaurantName();
        allFood = restaurantData.getRestaurantData();

        Collections.sort(allFood);

        for (Meals single : allFood) {
            addMenu(single.getSpecies(), single.getName(), single.getPrice());
        }
    }

    private void addMenu(String species, String food, int money) {

        if (!oldSpecies.equals(species)) {
            oldSpecies = species;
            TextView meun = new TextView(getContext());
            meun.setLayoutParams(new LinearLayout.LayoutParams(pixelToDp(350), LinearLayout.LayoutParams.WRAP_CONTENT));
            meun.setText(species);
            meun.setPadding(3, 0, 0, 0);
            meun.setTextSize(25);
            meun.setGravity(Gravity.CENTER);
            meun.setBackgroundResource(R.drawable.title);
            meun.setTextColor(Color.BLUE);
            display.addView(meun);
            addMenu(species, food, money);
        } else {
            TextView meun = new TextView(getContext());
            meun.setLayoutParams(new LinearLayout.LayoutParams(pixelToDp(300), LinearLayout.LayoutParams.WRAP_CONTENT));
            meun.setText(food + "\t" + money);
            meun.setPadding(3, 0, 0, 0);
            meun.setTextSize(20);
            meun.setGravity(Gravity.RIGHT);
            meun.setBackgroundResource(R.drawable.outside);
            meun.setTextColor(Color.WHITE);
            foodKey.put((food + money), new Meals(getArguments().getString("username"), food, money, species));
            meun.setTag(food + money);
            meun.setOnClickListener(selFood);
            display.addView(meun);
        }
    }

    private TextView.OnClickListener selFood = new TextView.OnClickListener() {

        @Override
        public void onClick(View view) {
            final Meals meals = new Meals(foodKey.get(view.getTag()).getUsername(), foodKey.get(view.getTag()).getName(), foodKey.get(view.getTag()).getPrice(), foodKey.get(view.getTag()).getSpecies());
            new AlertDialog.Builder(getContext())
                    .setTitle("訂單確認")
                    .setMessage("選擇 : " + foodKey.get(view.getTag()).getName() + " - " + foodKey.get(view.getTag()).getPrice() + " 元 確定要送出訂單?")
                    .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AddMeals addMeals = new AddMeals(meals);
                            if (addMeals.add())
                                Toast.makeText(getContext(), "訂單已送出，請至目前訂單確認", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();
        }
    };

    public int pixelToDp(float px) {
        return (int) (px * (this.getResources().getDisplayMetrics().density));
    }

    @Override
    public void onDestroy() {
        new RestaurantData().setRestaurant("null");
        new AddMeals().delete();
        super.onDestroy();
    }
}

