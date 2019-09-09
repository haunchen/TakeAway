package software.app.takeaway;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyContext.setContext(MainActivity.this);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewpager);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        tabLayout.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#0ddcff"));

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#0ddcff"));
        fragments = new ArrayList<>();

        Fragment f1 = MealsActivity.newInstance("目前訂單");
        f1.setArguments(getIntent().getExtras());

        Fragment f2 = FoodMenuActivity.newInstance("餐廳菜單");
        f2.setArguments(getIntent().getExtras());

        Fragment f3 = SetTimeActivity.newInstance("時限設定");
        f3.setArguments(getIntent().getExtras());

        Fragment f4 = ModifyMemberActivity.newInstance("資料修改");
        f4.setArguments(getIntent().getExtras());

        fragments.add(f1);
        fragments.add(f2);
        fragments.add(f3);
        fragments.add(f4);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "目前訂單";
                    case 1:
                        return "餐廳菜單";
                    case 2:
                        return " 時限設定";
                    case 3:
                        return "資料修改";
                }
                return "没有標題";
            }
        });

        tabLayout.setupWithViewPager(viewPager);
    }
}