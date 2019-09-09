package software.app.takeaway;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteMemberActivity extends AppCompatActivity {
    private Button done_btn;
    private EditText delete_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_member);

        MyContext.setContext(DeleteMemberActivity.this);

        done_btn = findViewById(R.id.done_btn);
        delete_et = findViewById(R.id.delete_et);

        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = delete_et.getText().toString();

                if (!str.equals("")) {
                    DeleteMember dm = new DeleteMember(str);
                    if (dm.searchData()) {
                        dm.delete();
                        finish();
                    } else
                        Toast.makeText(DeleteMemberActivity.this, "帳號不存在", Toast.LENGTH_SHORT).show();
                } else {
                    new AlertDialog.Builder(DeleteMemberActivity.this)
                            .setTitle("請輸入帳號")
                            .setMessage("")
                            .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }).show();
                }
            }
        });
    }
}
