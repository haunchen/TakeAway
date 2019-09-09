package software.app.takeaway;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class MealsActivity extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    private LinkedList<Meals> allOrder;
    private TableLayout display;
    private int money = 0;

    public static MealsActivity newInstance(String param) {
        MealsActivity fragment = new MealsActivity();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_order, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MyContext.setContext(getContext());

        display = getView().findViewById(R.id.allOrder);
        money = 0;
        allOrder = new GetMeals().getData();

        for (Meals single : allOrder) {
            display.addView(addOrder(single.getUsername(), single.getName(), single.getPrice()));
            money += single.getPrice();
        }
        display.addView(addSum(allOrder.size(), money));
    }

    private TableRow addSum(int item, int money) {
        TableRow row = new TableRow(getContext());
        row.addView(addUser("總計"));
        row.addView(addFood(item + " 項餐點"));
        row.addView(addMoney(money));

        return row;
    }

    private TableRow addOrder(String user, String food, int money) {
        TableRow row = new TableRow(getContext());
        row.addView(addUser(user));
        row.addView(addFood(food));
        row.addView(addMoney(money));
        if (getArguments().getString("username").equals(user) || getArguments().getBoolean("takeaway")) {
            if (Debug.ENABLE)
                Toast.makeText(getContext(), getArguments().getString("username"), Toast.LENGTH_SHORT).show();
            row.addView(addButton(user + food + money));
        }
        row.setTag(user + food + money);
        return row;
    }

    private TextView addUser(String user) {
        TextView meun = new TextView(getContext());
        meun.setLayoutParams(new TableRow.LayoutParams(pixelToDp(100), TableRow.LayoutParams.WRAP_CONTENT));
        meun.setText(user);
        meun.setPadding(3, 0, 0, 0);
        meun.setTextSize(20);
        meun.setBackgroundResource(R.drawable.outside);
        meun.setGravity(Gravity.CENTER);
        meun.setTextColor(Color.WHITE);
        return meun;
    }

    private TextView addFood(String food) {
        TextView meun = new TextView(getContext());
        meun.setLayoutParams(new TableRow.LayoutParams(pixelToDp(150), TableRow.LayoutParams.WRAP_CONTENT));
        meun.setText(food);
        meun.setPadding(3, 0, 0, 0);
        meun.setTextSize(20);
        meun.setGravity(Gravity.CENTER);
        meun.setBackgroundResource(R.drawable.outside);
        meun.setTextColor(Color.WHITE);
        return meun;
    }

    private TextView addMoney(int money) {
        TextView meun = new TextView(getContext());
        meun.setLayoutParams(new TableRow.LayoutParams(pixelToDp(70), TableRow.LayoutParams.WRAP_CONTENT));
        meun.setText(money + " 元");
        meun.setPadding(3, 0, 0, 0);
        meun.setTextSize(20);
        meun.setGravity(Gravity.CENTER);
        meun.setBackgroundResource(R.drawable.outside);
        meun.setTextColor(Color.WHITE);
        return meun;
    }

    private ImageButton addButton(String key) {
        ImageButton bt = new ImageButton(getContext());

        bt.setLayoutParams(new TableRow.LayoutParams(pixelToDp(50), pixelToDp(43)));
        bt.setBackgroundResource(R.drawable.ic_menu_delete);
        bt.setTag(key);
        bt.setOnClickListener(delete);

        return bt;
    }

    private ImageButton.OnClickListener delete = new ImageButton.OnClickListener() {
        @Override
        public void onClick(View view) {
            LinkedList<Meals> list = new GetMeals().getData();
            String str;

            for(int x = 0;x < list.size(); x++){
                str = list.get(x).getUsername() + list.get(x).getName() + list.get(x).getPrice();
                if(view.getTag().toString().equals(str))
                    new DeleteMeals(list.get(x)).delete();
            }
            display.removeView(display.findViewWithTag(view.getTag().toString()));
        }
    };

    public int pixelToDp(float px) {
        return (int) (px * (getContext().getResources().getDisplayMetrics().density));
    }


}
