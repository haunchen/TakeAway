package software.app.takeaway;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgetPasswordActivity extends AppCompatActivity {
    private Button done_btn, cancel_btn;
    private EditText pwd_et, pwdcheck_et;
    private String username, pwd, pwdcheck;
    private ForgetPassword fp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        MyContext.setContext(ForgetPasswordActivity.this);

        done_btn = findViewById(R.id.done_btn);
        cancel_btn = findViewById(R.id.cancel_btn);
        pwd_et = findViewById(R.id.pwd_et);
        pwdcheck_et = findViewById(R.id.pwdcheck_et);

        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");

        pwd = pwd_et.getText().toString();
        pwdcheck = pwdcheck_et.getText().toString();

        fp = new ForgetPassword(username, pwd);

        if (!fp.searchData()) {
            Toast.makeText(ForgetPasswordActivity.this, "帳號不存在", Toast.LENGTH_SHORT).show();
            finish();
        }

        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!pwd.equals("") && !pwdcheck.equals("")) {
                    if (pwd.equals(pwdcheck)) {
                        fp.update();
                        finish();
                    } else
                        Toast.makeText(ForgetPasswordActivity.this, "密碼不一致，請重新輸入", Toast.LENGTH_SHORT).show();
                } else {
                    new AlertDialog.Builder(ForgetPasswordActivity.this)
                            .setTitle("請輸入密碼")
                            .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }).show();
                }
            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
