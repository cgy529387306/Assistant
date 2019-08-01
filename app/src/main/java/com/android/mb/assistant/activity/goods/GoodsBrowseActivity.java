package com.android.mb.assistant.activity.goods;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.adapter.MyFragmentPagerAdapter;
import com.android.mb.assistant.base.BaseMvpActivity;
import com.android.mb.assistant.constants.CodeConstants;
import com.android.mb.assistant.entitys.DicBean;
import com.android.mb.assistant.entitys.DicListResp;
import com.android.mb.assistant.fragment.GoodsBrowseFragment;
import com.android.mb.assistant.presenter.CommonPresenter;
import com.android.mb.assistant.utils.Helper;
import com.android.mb.assistant.utils.JsonHelper;
import com.android.mb.assistant.utils.PreferencesHelper;
import com.android.mb.assistant.view.interfaces.ICommonView;
import com.android.mb.assistant.widget.FragmentViewPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 物资浏览
 */
public class GoodsBrowseActivity  extends BaseMvpActivity<CommonPresenter, ICommonView> implements ICommonView{
    private FragmentViewPager mFragmentViewPager;
    private TabLayout mTabLayout;
    private ArrayList<Fragment> mFragmentArrayList;

    @Override
    protected void loadIntent() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_browse;
    }

    @Override
    protected void initTitle() {
        setTitleText("物资浏览");
    }

    @Override
    protected void bindViews() {
        initView();
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        String result = PreferencesHelper.getInstance().getString("category");
        if (Helper.isNotEmpty(result)){
            dealResult(result);
        }else{
            getListFormServer();
        }
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

    private void initTabViewPager(List<DicBean> dicBeanList){
        mFragmentArrayList = new ArrayList<>();
        mFragmentArrayList.add(GoodsBrowseFragment.getInstance("全部"));
        setTabName("全部");
        for(DicBean dicBean:dicBeanList){
            setTabName(dicBean.getDictname());
            mFragmentArrayList.add(GoodsBrowseFragment.getInstance(dicBean.getDictname()));
        }
        mFragmentViewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragmentArrayList));
        mFragmentViewPager.setOffscreenPageLimit(mFragmentArrayList.size());
        mFragmentViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
    }

    private void setTabName(String name){
        if (Helper.isEmpty(name)){
            name = "全部";
        }
        TabLayout.Tab tab = mTabLayout.newTab();
        View view = LayoutInflater.from(GoodsBrowseActivity.this).inflate(R.layout.chapter_approval_tab,null);
        tab.setCustomView(view);

        TextView tvTitle = view.findViewById(R.id.tv_tab);
        tvTitle.setText(name);
        mTabLayout.addTab(tab);
    }

    private void getListFormServer(){
        Map<String,String> requestParams = new HashMap<>();
        requestParams.put("parno","5");
        requestParams.put("page",String.valueOf(mCurrentPage));
        requestParams.put("rows",String.valueOf(Integer.MAX_VALUE));
        mPresenter.requestData(CodeConstants.KEY_COMMON_DIC,requestParams,false);
    }

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter();
    }

    @Override
    public void requestSuccess(String requestCode, String result) {

    }

    private void dealResult(String result){
        DicListResp listResp = JsonHelper.fromJson(result, DicListResp.class);
        if (listResp!=null && Helper.isNotEmpty(listResp.getData())){
            initTabViewPager(listResp.getData());
        }
    }
}
