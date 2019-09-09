package software.app.takeaway;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModifyMemberActivity extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private EditText name_et, pwd_et, pwdcheck_et, email_et;
    private Button done_btn;
    private String username;
    private Account account;

    public static ModifyMemberActivity newInstance(String param) {
        ModifyMemberActivity fragment = new ModifyMemberActivity();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_modify_member, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MyContext.setContext(getContext());

        Bundle bundle = getArguments();
        username = bundle.getString("username");

        name_et = getView().findViewById(R.id.name_et);
        pwd_et = getView().findViewById(R.id.pwd_et);
        pwdcheck_et = getView().findViewById(R.id.pwdcheck_et);
        email_et = getView().findViewById(R.id.email_et);
        done_btn = getView().findViewById(R.id.done_btn);

        account = new ModifyMember(username).getUsernameData();

        if (account != null) {
            name_et.setText(account.getName());
            pwd_et.setText(account.getPassword());
            email_et.setText(account.getEmail());
        }

        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModifyMember mm = new ModifyMember(name_et.getText().toString(), username, pwd_et.getText().toString(), email_et.getText().toString());

                if (!pwdcheck_et.getText().toString().equals("")) {
                    if (pwdcheck_et.getText().toString().equals(account.getPassword())) {
                        mm.modify();
                    } else
                        Toast.makeText(getContext(), "密碼錯誤，請重新輸入", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getContext(), "請再次輸入原密碼", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
