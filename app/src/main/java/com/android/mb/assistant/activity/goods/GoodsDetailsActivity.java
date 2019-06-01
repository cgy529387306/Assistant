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
import com.android.mb.assistant.fragment.GoodsBrowseFragment;
import com.android.mb.assistant.widget.FragmentViewPager;

import java.util.ArrayList;

/**
 * 物资详情
 */
public class GoodsDetailsActivity extends BaseActivity{

    @Override
    protected void loadIntent() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_details;
    }

    @Override
    protected void initTitle() {
        setTitleText("物资详情");
    }

    @Override
    protected void bindViews() {
        initView();
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {

    }

    private void initView() {
    }

}
