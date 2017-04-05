package com.firstapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by stgqq on 2017/3/29.
 */



public class ToastActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//不在活动中显示标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏通知栏
        setContentView(R.layout.toast_activity);
        Button button1 = (Button) findViewById(R.id.btn_toast);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Toast.makeText(ToastActivity.this,"哈哈",Toast.LENGTH_SHORT).show();

                Toast.makeText(ToastActivity.this,"你按了按钮键",Toast.LENGTH_SHORT).show();

            }
        });

    }
}
