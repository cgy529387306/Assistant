package com.android.mb.assistant.activity.competitive;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.adapter.MyFragmentPagerAdapter;
import com.android.mb.assistant.base.BaseActivity;
import com.android.mb.assistant.fragment.CompetitiveBrowseFragment;
import com.android.mb.assistant.fragment.CompetitiveWorkFragment;
import com.android.mb.assistant.widget.FragmentViewPager;

import java.util.ArrayList;

/**
 * 竞情查看
 */
public class CompetitiveBrowseActivity extends BaseActivity{
    private FragmentViewPager mFragmentViewPager;
    private TabLayout mTabLayout;
    private ArrayList<Fragment> mFragmentArrayList;
    private int mCurrentPage;
    @Override
    protected void loadIntent() {
        mCurrentPage = getIntent().getIntExtra("page",0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_browse;
    }

    @Override
    protected void initTitle() {
        setTitleText("竞情查看");
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
                }else if (tab.getPosition()==3){
                    mFragmentViewPager.setCurrentItem(tab.getPosition(),true);
                }else if (tab.getPosition()==4){
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
        mFragmentArrayList.add(CompetitiveBrowseFragment.getInstance(99));
        mFragmentArrayList.add(CompetitiveBrowseFragment.getInstance(1));
        mFragmentArrayList.add(CompetitiveBrowseFragment.getInstance(2));
        mFragmentArrayList.add(CompetitiveWorkFragment.getInstance());
        mFragmentArrayList.add(CompetitiveBrowseFragment.getInstance(3));
        mFragmentArrayList.add(CompetitiveBrowseFragment.getInstance(4));
        mFragmentArrayList.add(CompetitiveBrowseFragment.getInstance(5));
        mFragmentViewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragmentArrayList));
        mFragmentViewPager.setOffscreenPageLimit(mFragmentArrayList.size());
        mFragmentViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mFragmentViewPager.setCurrentItem(mCurrentPage);
        setViewTabs();
    }
    /**
     * @description: 设置添加Tab
     */
    private void setViewTabs(){
        int[] tabTitles = new int[]{R.string.tab_all,R.string.tab_no_order,R.string.tab_have_order,R.string.tab_my_order,R.string.tab_get_success,R.string.tab_get_fail,R.string.tab_no_read};
        for (int i = 0; i < tabTitles.length; i++) {
            TabLayout.Tab tab = mTabLayout.newTab();
            View view = LayoutInflater.from(CompetitiveBrowseActivity.this).inflate(R.layout.chapter_approval_tab,null);
            tab.setCustomView(view);

            TextView tvTitle = view.findViewById(R.id.tv_tab);
            tvTitle.setText(tabTitles[i]);
            mTabLayout.addTab(tab);
        }
    }

}
