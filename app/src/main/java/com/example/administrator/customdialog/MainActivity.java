package com.example.administrator.customdialog;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.showQuestionDialog(MainActivity.this, "", "下载失败，请重试！！", "", "",true,
                        new DialogUtils.OnClickYesListener() {
                            @Override
                            public void onClickYes(Dialog dialog) {
                                Toast.makeText(MainActivity.this, "yes", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }, new DialogUtils.OnClickNoListener() {
                            @Override
                            public void onClickNo(Dialog dialog) {
                                Toast.makeText(MainActivity.this, "no", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
            }
        });

    }
}
