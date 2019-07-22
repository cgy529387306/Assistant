package com.android.mb.assistant.activity.goods;

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
import com.android.mb.assistant.entitys.CompetitiveBean;
import com.android.mb.assistant.entitys.GoodsBean;
import com.android.mb.assistant.fragment.GoodsBrowseFragment;
import com.android.mb.assistant.utils.Helper;
import com.android.mb.assistant.widget.FragmentViewPager;

import java.util.ArrayList;

/**
 * 物资详情
 */
public class GoodsDetailsActivity extends BaseActivity{

    private GoodsBean mGoodsBean;
    private ImageView mIvGoods;
    private TextView mTvName,mTvModel,mTvDepartment;
    private TextView mTvCategory,mTvStorageSite,mTvNumber,mTvPrice;
    private TextView mTvIsAsset,mTvGsDepartment,mTvInputTime,mTvContacts;

    @Override
    protected void loadIntent() {
        mGoodsBean = (GoodsBean) getIntent().getSerializableExtra("goods");
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
        mIvGoods = findViewById(R.id.iv_goods);
        mTvName = findViewById(R.id.tv_name);
        mTvModel = findViewById(R.id.tv_model);
        mTvDepartment = findViewById(R.id.tv_department);
        mTvCategory = findViewById(R.id.tv_category);
        mTvStorageSite = findViewById(R.id.tv_storage_site);
        mTvNumber = findViewById(R.id.tv_number);
        mTvPrice = findViewById(R.id.tv_price);
        mTvIsAsset = findViewById(R.id.tv_is_asset);
        mTvGsDepartment = findViewById(R.id.tv_gs_department);
        mTvInputTime = findViewById(R.id.tv_input_time);
        mTvContacts = findViewById(R.id.tv_contacts);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        initData();
    }

    private void initData(){
        if (mGoodsBean!=null){
            mTvName.setText(mGoodsBean.getMaterialName());
            mTvModel.setText(mGoodsBean.getUnitname());
            mTvDepartment.setText(mGoodsBean.getGsDepartment());
            mTvCategory.setText(mGoodsBean.getGsDepartment());
            mTvStorageSite.setText(mGoodsBean.getStorageSite());
            mTvNumber.setText(String.valueOf(mGoodsBean.getMum()));
            mTvIsAsset.setText(mGoodsBean.getAsset()==0?"是":"否");
            mTvGsDepartment.setText(mGoodsBean.getGsDepartment());
            mTvInputTime.setText(Helper.long2DateString(mGoodsBean.getCreateTime(),Helper.DATE_FORMAT1));
            mTvContacts.setText(mGoodsBean.getContacts());
        }
    }

    @Override
    protected void setListener() {

    }


}
