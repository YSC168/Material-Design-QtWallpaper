package us.wili.qtwallpaper.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import us.wili.qtwallpaper.R;
import us.wili.qtwallpaper.adapter.DisplayPagerAdapter;
import us.wili.qtwallpaper.model.WallpaperItem;

/**
 * 展示壁纸的Activity
 * Created by qiu on 1/19/16.
 */
public class WallPaperDisplayActivity extends AppCompatActivity {

    public static final String WALLPAPERS = "wallpapers";
    public static final String INDEX = "index";

    public static Intent getIntent(Context context, ArrayList<WallpaperItem> items, int index) {
        Intent intent = new Intent(context, WallPaperDisplayActivity.class);
        intent.putParcelableArrayListExtra(WALLPAPERS, items);
        intent.putExtra(INDEX, index);
        return intent;
    }

    private Handler delayLoadHandler;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initViews();

        initData();

        delayLoadHandler = new Handler();

        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                delayLoadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onDelayLoad();
                    }
                });
            }
        });
    }

    protected void initViews() {
        setContentView(R.layout.activity_wall_paper_display);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
    }

    protected void initData() {
        Intent intent = getIntent();
        int index = intent.getIntExtra(INDEX, 0);
        ArrayList<WallpaperItem> items = intent.getParcelableArrayListExtra(WALLPAPERS);
        DisplayPagerAdapter mAdapter = new DisplayPagerAdapter(this, items);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(index);
    }

    protected void onDelayLoad() {

    }
}