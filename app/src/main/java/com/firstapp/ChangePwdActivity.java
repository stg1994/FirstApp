package com.firstapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by stgqq on 2017/3/31.
 */

public class ChangePwdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepwd_activity);

        final EditText changeusers =  (EditText) findViewById(R.id.et_changeusers);
        Button changepwd = (Button) findViewById(R.id.btn_changepwd);

        changepwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String users = changeusers.getText().toString().trim();
                if (!users.isEmpty()){
                    Toast.makeText(getApplicationContext(),"请输入完整数据",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(),"稍后在继续写这程序",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    }
