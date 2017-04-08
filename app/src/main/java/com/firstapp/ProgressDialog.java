package com.firstapp;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.firstapp.progressDialog.CustomProgressDialog;

/**
 * Created by stgqq on 2017/4/8.
 */

public class ProgressDialog extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedIntanceState){
        super.onCreate(savedIntanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//不在活动中显示标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏通知栏
        setContentView(R.layout.progressdialog_activity);
        Button button = (Button) findViewById(R.id.btn_pDialog);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog pDialog = CustomProgressDialog.createLoadingDialog(ProgressDialog.this,"正在加载...");
                pDialog.setCancelable(true);
                pDialog.show();
            }
        });

    }
}
