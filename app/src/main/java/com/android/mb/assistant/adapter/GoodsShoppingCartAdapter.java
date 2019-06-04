package com.android.mb.assistant.adapter;

import com.android.mb.assistant.R;
import com.android.mb.assistant.entitys.ShoppingCartData;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


/**
 * Created by necer on 2017/6/7.
 */
public class GoodsShoppingCartAdapter extends BaseQuickAdapter<ShoppingCartData, BaseViewHolder> {

    public GoodsShoppingCartAdapter(List data) {
        super(R.layout.item_goods_shopping_cart, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, ShoppingCartData item) {
        helper.setText(R.id.tv_name,item.getName());
        helper.setImageResource(R.id.iv_check,item.isSelect()?R.mipmap.icon_select_on:R.mipmap.icon_select_off);
    }
}




