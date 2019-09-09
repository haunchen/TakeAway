package software.app.takeaway;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    private Button reset_btn, done_btn, cancel_btn;
    private EditText name_et, username_et, password_et, email_et;
    private String name, username, password, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        done_btn = findViewById(R.id.done_btn);
        reset_btn = findViewById(R.id.reset_btn);
        cancel_btn = findViewById(R.id.cancel_btn);
        name_et = findViewById(R.id.name_et);
        username_et = findViewById(R.id.username_et);
        password_et = findViewById(R.id.password_et);
        email_et = findViewById(R.id.email_et);

        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = name_et.getText().toString();
                username = username_et.getText().toString();
                password = password_et.getText().toString();
                email = email_et.getText().toString();

                if (name.matches("") || username.matches("") || password.matches("")) {
                    new AlertDialog.Builder(RegisterActivity.this)
                            .setTitle("有空格")
                            .setMessage("請確實填寫!!")
                            .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }).show();
                } else {
                    //產生視窗物件
                    new AlertDialog.Builder(RegisterActivity.this)
                            .setTitle("歡迎您!!!")//設定視窗標題
                            .setIcon(R.mipmap.ic_eat)//設定對話視窗圖示
                            .setMessage("確定要註冊嗎?")//設定顯示的文字
                            .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Register r = new Register(name, username, password, email);
                                    if (r.insert())
                                        finish();
                                    else
                                        setEditTextEmpty();
                                }
                            })//設定結束的子視窗
                            .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    setEditTextEmpty();
                                }
                            })
                            .show();//呈現對話視窗
                }
            }
        });

        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEditTextEmpty();
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setEditTextEmpty() {
        name_et.setText("");
        username_et.setText("");
        password_et.setText("");
        email_et.setText("");
    }
}
