package software.app.takeaway;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private int record;
    private String[][] restaurant = {{"阿寶", "王品", "一番"}, {"abao", "wangpin", "yifan"}};
    private Button register_btn, login_bt, forgetpwd_btn;
    private EditText user_account, pass_word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        MyContext.setContext(LoginActivity.this);

        login_bt = findViewById(R.id.login_bt);
        register_btn = findViewById(R.id.register_btn);
        forgetpwd_btn = findViewById(R.id.forget_btn);
        user_account = findViewById(R.id.accountText);
        pass_word = findViewById(R.id.passwordText);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = user_account.getText().toString();
                final String password = pass_word.getText().toString();

                if (!username.equals("") && !password.equals("")) {
                    final Login login = new Login(username, password);
                    if (login.isMember()) {
                        Toast.makeText(LoginActivity.this, "登入成功", Toast.LENGTH_SHORT).show();
                        final String[] takeawaystr = {"被外帶者", "外帶者"};
                        final int[] takeawaychoice = {0};
                        final Bundle bundle = new Bundle();

                        new AlertDialog.Builder(LoginActivity.this)
                                .setTitle("請選擇 :")
                                .setIcon(R.mipmap.ic_eat)
                                .setSingleChoiceItems(takeawaystr, 0,
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                takeawaychoice[0] = which;
                                                Toast.makeText(LoginActivity.this, "您選擇的是 " + takeawaystr[takeawaychoice[0]], Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();

                                        if (takeawaychoice[0] == 1) {
                                            new AlertDialog.Builder(LoginActivity.this)
                                                    .setTitle("請選擇 :")
                                                    .setIcon(R.mipmap.ic_eat)
                                                    .setSingleChoiceItems(restaurant[0], 0,
                                                            new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {
                                                                    record = which;
                                                                    Toast.makeText(LoginActivity.this, "您選擇的是 " + restaurant[record], Toast.LENGTH_SHORT).show();
                                                                }
                                                            })
                                                    .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            dialog.dismiss();
                                                            new RestaurantData().setRestaurant(restaurant[1][record]);
                                                            SetTakeaway st = new SetTakeaway(username, takeawaychoice[0] == 1);
                                                            st.update();
                                                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                                                            bundle.putString("username", username);
                                                            bundle.putBoolean("isadministrator", login.isAdministrator());
                                                            bundle.putBoolean("takeaway", login.isTakeaway());
                                                            intent.putExtras(bundle);
                                                            startActivity(intent);
                                                        }
                                                    })
                                                    .show();
                                        } else {
                                            RestaurantData restaurantData = new RestaurantData();
                                            restaurantData.getRestaurantName();
                                            if (restaurantData.getRestaurant().equals("null")) {
                                                Toast.makeText(LoginActivity.this, "外帶者尚未決定", Toast.LENGTH_SHORT).show();
                                            } else {
                                                SetTakeaway st = new SetTakeaway(username, takeawaychoice[0] == 1);
                                                st.update();
                                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                                                bundle.putString("username", username);
                                                bundle.putBoolean("isadministrator", login.isAdministrator());
                                                bundle.putBoolean("takeaway", login.isTakeaway());
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                            }
                                        }
                                        user_account.setText("");
                                        pass_word.setText("");
                                    }
                                })
                                .show();

                    } else {
                        new AlertDialog.Builder(LoginActivity.this)
                                .setTitle("錯誤!!")//設定視窗標題
                                .setIcon(R.mipmap.ic_eat)//設定對話視窗圖示
                                .setMessage("請輸入正確的帳號或密碼")//設定顯示的文字
                                .setPositiveButton("返回", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        user_account.setText("");
                                        pass_word.setText("");
                                    }
                                })
                                .show();//呈現對話視窗
                    }
                } else {
                    new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("錯誤!!")//設定視窗標題
                            .setIcon(R.mipmap.ic_eat)//設定對話視窗圖示
                            .setMessage("帳號或密碼不得為空白")//設定顯示的文字
                            .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .show();
                }
            }
        });

        forgetpwd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user_account.getText().toString();

                if (!username.equals("")) {
                    Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                    Bundle bundle = new Bundle();

                    bundle.putString("username", username);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else
                    new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("請輸入帳號")
                            .setPositiveButton("好", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }).show();
            }
        });
    }
}
