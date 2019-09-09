package software.app.takeaway;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

public class SetTimeActivity extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    private TimePicker deadline;
    private Button saveTime;


    public static SetTimeActivity newInstance(String param) {
        SetTimeActivity fragment = new SetTimeActivity();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param);
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_set_time, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MyContext.setContext(getContext());

        deadline = getView().findViewById(R.id.deadline);
        saveTime = getView().findViewById(R.id.saveTime);
        saveTime.setBackgroundResource(R.drawable.button);

        saveTime.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    String time = deadline.getHour()+":"+deadline.getMinute();
                    Log.v("Time",time);
                }
            }
        });


    }

}
