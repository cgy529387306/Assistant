package com.android.mb.assistant.adapter;

import com.android.mb.assistant.R;
import com.android.mb.assistant.entitys.CartBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


/**
 * Created by necer on 2017/6/7.
 */
public class GoodsApplyUseAdapter extends BaseQuickAdapter<CartBean, BaseViewHolder> {

    public GoodsApplyUseAdapter(List data) {
        super(R.layout.item_goods_apply_use, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, CartBean item) {
        helper.setText(R.id.tv_serial,String.valueOf(helper.getAdapterPosition()+1));
        helper.setText(R.id.tv_name,item.getMaterialName());
        helper.setText(R.id.tv_number,String.valueOf(item.getMaterialNum()));
        helper.setText(R.id.tv_price,String.valueOf(item.getMaterialPrice()));
    }
}




