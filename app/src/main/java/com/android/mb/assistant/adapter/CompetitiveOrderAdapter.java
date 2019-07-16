package com.android.mb.assistant.adapter;

import com.android.mb.assistant.R;
import com.android.mb.assistant.entitys.CompetitiveBean;
import com.android.mb.assistant.utils.Helper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


/**
 * Created by necer on 2017/6/7.
 */
public class CompetitiveOrderAdapter extends BaseQuickAdapter<CompetitiveBean, BaseViewHolder> {

    public CompetitiveOrderAdapter(List data) {
        super(R.layout.item_competitive_order, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, CompetitiveBean item) {
        helper.setText(R.id.tv_order_time, item.getCCreateTime());
        helper.setText(R.id.tv_name,item.getCUsername());
        helper.setText(R.id.tv_tel,item.getCMobile());
        helper.setText(R.id.tv_address,item.getCAdd());

    }
}




