package com.firstapp;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by stgqq on 2017/4/8.
 * 自定义圆形的进度条
 *
 */

public class CustomProgressDialog {
    /**
     * 得到自定义的progressDialog
     * @param context
     * @param msg
     * @return
     */

    public static Dialog createLoadingDialog(Context context, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.loading_dialog_activity,null);//得到加载View
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.dialog_view);//加载布局
        //mian.xml中的ImageView
        ImageView spaceshipImage = (ImageView) view.findViewById(R.id.img);
        TextView tipTextView = (TextView) view.findViewById(R.id.tipTextView); //提示文字
        //加载动画
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(context,R.anim.load_animation);
        //使用ImageView显示动画
        spaceshipImage.startAnimation(hyperspaceJumpAnimation);
        tipTextView.setText(msg);//设置加载信息
        Dialog loadingDialog = new Dialog(context,R.style.loading_dialog);//创建自定义样式Dialog
        loadingDialog.setCancelable(false);//不可以用“返回键”取消
        loadingDialog.setContentView(layout,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT) );//设置布局
        return loadingDialog;
    }
}
