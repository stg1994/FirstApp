package com.firstapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.firstapp.db.SQLiteHandler;
import com.firstapp.db.SessionManager;
import com.firstapp.progressDialog.CustomProgressDialog;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by stgqq on 2017/3/29.
 */

public class MainActivity extends AppCompatActivity {
    private List<Listitem> Listitem = new ArrayList<Listitem>();
    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//不在活动中显示标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏通知栏
        setContentView(R.layout.main_activity);
        initListitem();//初始化事件
        ListitemAdapter adapter = new ListitemAdapter(MainActivity.this,R.layout.list_item,Listitem);
        ListView listView = (ListView) findViewById(R.id.first_app);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Log.i("mm"," onItemClick ");
                Intent intent = new Intent();
                switch (position){
                    case 0:
                    {
                        Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_LONG).show();
                    }
                        break;
                    case 1:
                        intent.setClass(getApplicationContext(),com.firstapp.refresh.FlyRefreshActivity.class);
                        MainActivity.this.startActivity(intent);
                        break;
                    case 2:
                        logoutUser();
                        break;
                    case 3:{
                        Dialog pDialog = CustomProgressDialog.createLoadingDialog(MainActivity.this,"正在加载...");
                        pDialog.setCancelable(true);
                        pDialog.show();
                    }
                        break;
                    case 4:
                        intent.setClass(getApplicationContext(),com.firstapp.DeleteUserActivity.class);
                        MainActivity.this.startActivity(intent);
                        break;
                }
            }
        });

        // SqLite database handler连接本地数据库
        db = new SQLiteHandler(getApplicationContext());
        // session manager 退出用户登录
        session = new SessionManager(getApplicationContext());
        if (!session.isLoggedIn()) {
            logoutUser();
        }

    }

    private void initListitem(){
        Listitem apple = new Listitem("Toast显示", R.drawable.wedding_19);
        Listitem.add(apple);
        Listitem refresh = new Listitem("刷新动画", R.drawable.wedding_30);
        Listitem.add(refresh);
        Listitem logout = new Listitem("退出登录", R.drawable.wedding_19);
        Listitem.add(logout);
        Listitem watermelon = new Listitem("显示进度", R.drawable.wedding_30);
        Listitem.add(watermelon);
        Listitem pear = new Listitem("删除用户", R.drawable.wedding_19);
        Listitem.add(pear);
        Listitem grape = new Listitem("Grape", R.drawable.wedding_30);
        Listitem.add(grape);
        Listitem pineapple = new Listitem("Pineapple", R.drawable.wedding_19);
        Listitem.add(pineapple);
        Listitem strawberry = new Listitem("Strawberry", R.drawable.wedding_30);
        Listitem.add(strawberry);
        Listitem cherry = new Listitem("Cherry", R.drawable.wedding_19);
        Listitem.add(cherry);
        Listitem mango = new Listitem("Mango",R.drawable.wedding_30);
        Listitem.add(mango);
    }



    /**
     * 退出登录，清除本地sqlite数据
     */
    private void logoutUser() {
        session.setLogin(false);
        db.deleteUsers();
        // Launching the login activity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}

