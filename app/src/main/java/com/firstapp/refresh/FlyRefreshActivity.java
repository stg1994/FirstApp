package com.firstapp.refresh;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.firstapp.R;
import com.race604.flyrefresh.FlyRefreshLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by stgqq on 2017/4/3.
 */

public class FlyRefreshActivity extends AppCompatActivity implements FlyRefreshLayout.OnPullRefreshListener {
    private FlyRefreshLayout mFlylayout;
    private RecyclerView mListeView;

    private ItemAdapter mAdapter;

    private ArrayList<ItemData> mDataSet = new ArrayList<>();
    private Handler mHandler = new Handler();
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//不在活动中显示标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏通知栏
        initDataSet();
        setContentView(R.layout.flyrefresh_activity);

        mFlylayout = (FlyRefreshLayout) findViewById(R.id.fly_layout);
        mFlylayout.setOnPullRefreshListener(this);

        mListeView = (RecyclerView) findViewById(R.id.list);

        mLayoutManager = new LinearLayoutManager(this);
        mListeView.setLayoutManager(mLayoutManager);
        mAdapter = new ItemAdapter(this);
        mListeView.setAdapter(mAdapter);
        mListeView.setItemAnimator(new SampleItemAnimator());
        View actionButton = mFlylayout.getHeaderActionButton();
        if (actionButton != null) {
            actionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mFlylayout.startRefresh();
                }
            });
        }

    }


    private void initDataSet() {
        mDataSet.add(new ItemData(Color.parseColor("#76A9FC"), R.mipmap.ic_assessment_white_24dp, "Meeting Minutes", new Date(2014 - 1900, 4, 9)));
        mDataSet.add(new ItemData(Color.GRAY, R.mipmap.ic_folder_white_24dp, "Favorites Photos", new Date(2014 - 1900, 1, 3)));
        mDataSet.add(new ItemData(Color.GRAY, R.mipmap.ic_folder_white_24dp, "Photos", new Date(2014 - 1900, 0, 9)));
    }

    private void addItemData() {
        ItemData itemData = new ItemData(Color.parseColor("#FFC970"), R.mipmap.ic_smartphone_white_24dp, "Magic Cube Show", new Date());
        mDataSet.add(0, itemData);
        mAdapter.notifyItemInserted(0);
        mLayoutManager.scrollToPosition(0);
    }

    // @Override
    //  public boolean onCreateOptionsMenu(Menu menu){
    //     getMenuInflater().inflate(R.menu.menu_main,menu);
    //    return true;
    // }

    //  @Override
    //  public boolean onOptionsItemSelected(MenuItem item){
    //     int id = item.getItemId();
    //     if (id == R.id.action_settings){
    //        return true;
    //    }
    //    return super.onOptionsItemSelected(item);
    //  }

    @Override
    public void onRefresh(FlyRefreshLayout view) {
        View child = mListeView.getChildAt(0);
        if (child != null) {
            bounceAnimateView(child.findViewById(R.id.icon));
        }
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mFlylayout.onRefreshFinish();
            }
        }, 2000);
    }

    private void bounceAnimateView(View view) {
        if (view == null) {
            return;
        }
        Animator swing = ObjectAnimator.ofFloat(view, "rotationX", 0, 30, -20, 0);
        swing.setDuration(400);
        swing.setInterpolator(new AccelerateInterpolator());
        swing.start();
    }

    @Override
    public void onRefreshAnimationEnd(FlyRefreshLayout view) {
        addItemData();
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        private LayoutInflater mInflater;
        private DateFormat dateFormat;

        public ItemAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
            dateFormat = SimpleDateFormat.getDateInstance(DateFormat.DEFAULT, Locale.ENGLISH);
        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = mInflater.inflate(R.layout.flyrefresh_viewlistitem_activity, viewGroup, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
            final ItemData data = mDataSet.get(i);
            ShapeDrawable drawable = new ShapeDrawable(new OvalShape());
            drawable.getPaint().setColor(data.color);
            itemViewHolder.icon.setBackgroundDrawable(drawable);
            itemViewHolder.icon.setImageResource(data.icon);
            itemViewHolder.title.setText(data.title);
            itemViewHolder.subTitle.setText(dateFormat.format(data.time));
        }

        @Override
        public int getItemCount() {
            return mDataSet.size();
        }
    }

    private static class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView title;
        TextView subTitle;

        public ItemViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            title = (TextView) itemView.findViewById(R.id.title);
            subTitle = (TextView) itemView.findViewById(R.id.subtitle);
        }
    }

}
