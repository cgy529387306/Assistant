package com.android.mb.assistant.activity.goods;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.adapter.MyFragmentPagerAdapter;
import com.android.mb.assistant.base.BaseActivity;
import com.android.mb.assistant.fragment.ChapterApprovalFragment;
import com.android.mb.assistant.widget.FragmentViewPager;

import java.util.ArrayList;

/**
 * 用章审批
 */
public class ChapterApprovalActivity extends BaseActivity{
    private FragmentViewPager mFragmentViewPager;
    private TabLayout mTabLayout;
    private ArrayList<Fragment> mFragmentArrayList;
    private int status;
    @Override
    protected void loadIntent() {
        status = getIntent().getIntExtra("status",0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chapter_approval;
    }

    @Override
    protected void initTitle() {
        if (status == 0) {
            setTitleText("用章审批");
        }else if (status == 1){
            setTitleText("我的申请");
        }
    }

    @Override
    protected void bindViews() {
        initView();
        initTabViewPager();
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition()==0){
                    mFragmentViewPager.setCurrentItem(tab.getPosition(),true);
                }else if (tab.getPosition()==1){
                    mFragmentViewPager.setCurrentItem(tab.getPosition(),true);
                }else if (tab.getPosition()==2){
                    mFragmentViewPager.setCurrentItem(tab.getPosition(),true);
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
        mFragmentArrayList.add(ChapterApprovalFragment.getInstance(status,0));
        mFragmentArrayList.add(ChapterApprovalFragment.getInstance(status,1));
        mFragmentArrayList.add(ChapterApprovalFragment.getInstance(status,2));
        mFragmentViewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragmentArrayList));
        mFragmentViewPager.setOffscreenPageLimit(mFragmentArrayList.size());
        mFragmentViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        setViewTabs();
    }
    /**
     * @description: 设置添加Tab
     */
    private void setViewTabs(){
        int[] tabTitles = new int[]{R.string.tab_all,R.string.tab_no_pass,R.string.tab_pass};
        for (int i = 0; i < tabTitles.length; i++) {
            TabLayout.Tab tab = mTabLayout.newTab();
            View view = LayoutInflater.from(ChapterApprovalActivity.this).inflate(R.layout.chapter_approval_tab,null);
            tab.setCustomView(view);

            TextView tvTitle = view.findViewById(R.id.tv_tab);
            tvTitle.setText(tabTitles[i]);
            mTabLayout.addTab(tab);
        }
    }

}
