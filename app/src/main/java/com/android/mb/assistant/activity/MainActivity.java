package com.android.mb.assistant.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.adapter.MyFragmentPagerAdapter;
import com.android.mb.assistant.base.BaseActivity;
import com.android.mb.assistant.fragment.CompetitiveManageFragment;
import com.android.mb.assistant.fragment.GoodsManageFragment;
import com.android.mb.assistant.fragment.HomeFragment;
import com.android.mb.assistant.utils.ToastHelper;
import com.android.mb.assistant.widget.FragmentViewPager;
import com.pgyersdk.update.PgyUpdateManager;

import java.util.ArrayList;

public class MainActivity extends BaseActivity{
    private FragmentViewPager mFragmentViewPager;
    private TabLayout mTabLayout;
    private ArrayList<Fragment> mFragmentArrayList;
    private HomeFragment mHomeFragment;
    private CompetitiveManageFragment mCompetitiveManageFragment;
    private GoodsManageFragment mGoodsManageFragment;
    @Override
    protected void loadIntent() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initTitle() {
        hideActionBar();
    }

    @Override
    protected void bindViews() {
        initView();
        initTabViewPager();
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        PgyUpdateManager.setIsForced(false); //设置是否强制更新。true为强制更新；false为不强制更新（默认值）。
        PgyUpdateManager.register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PgyUpdateManager.unregister();
    }

    @Override
    protected void setListener() {
        //设置阴影
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            mTabLayout.setElevation(10);
        }
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition()==0){
                    //月视图
                    mFragmentViewPager.setCurrentItem(tab.getPosition(),true);
                    if (mHomeFragment!=null){
                    }
                }else if (tab.getPosition()==1){
                    //周视图
                    mFragmentViewPager.setCurrentItem(tab.getPosition(),true);
                    if (mCompetitiveManageFragment!=null){
                    }
                }else if (tab.getPosition()==2){
                    mFragmentViewPager.setCurrentItem(tab.getPosition(),true);
                    if (mGoodsManageFragment!=null){
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void initView() {
        mFragmentViewPager = findViewById(R.id.fragmentViewPager);
        mTabLayout = findViewById(R.id.tab_layout);
    }
    private void initTabViewPager(){
        mFragmentArrayList = new ArrayList<>();
        mHomeFragment = new HomeFragment();
        mCompetitiveManageFragment = new CompetitiveManageFragment();
        mGoodsManageFragment = new GoodsManageFragment();
        mFragmentArrayList.add(mHomeFragment);
        mFragmentArrayList.add(mCompetitiveManageFragment);
        mFragmentArrayList.add(mGoodsManageFragment);
        mFragmentViewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragmentArrayList));
        mFragmentViewPager.setOffscreenPageLimit(mFragmentArrayList.size());
        mFragmentViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        setViewTabs();
    }
    /**
     * @description: 设置添加Tab
     */
    private void setViewTabs(){
        int[] tabTitles = new int[]{R.string.tab_home,R.string.tab_competitive_manage,R.string.tab_goods_manage};
        int[] tabImages = new int[]{R.drawable.btn_tab_home,R.drawable.btn_tab_competitive,R.drawable.btn_tab_goods};
        for (int i = 0; i < tabImages.length; i++) {
            TabLayout.Tab tab = mTabLayout.newTab();
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_tab,null);
            tab.setCustomView(view);

            TextView tvTitle = view.findViewById(R.id.tv_tab);
            tvTitle.setText(tabTitles[i]);
            ImageView imgTab =  view.findViewById(R.id.iv_tab);
            imgTab.setImageResource(tabImages[i]);
            mTabLayout.addTab(tab);
        }
    }

    private static final long DOUBLE_CLICK_INTERVAL = 2000;
    private long mLastClickTimeMills = 0;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - mLastClickTimeMills > DOUBLE_CLICK_INTERVAL) {
            ToastHelper.showToast("再按一次返回退出");
            mLastClickTimeMills = System.currentTimeMillis();
            return;
        }
        finish();
    }
}
