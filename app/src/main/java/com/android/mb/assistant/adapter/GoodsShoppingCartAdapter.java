package com.android.mb.assistant.adapter;

import android.view.View;
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.entitys.ShoppingCartData;
import com.android.mb.assistant.utils.Helper;
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
        TextView tvNum = helper.getView(R.id.tv_num);
        helper.setText(R.id.tv_name,item.getName());
        helper.setImageResource(R.id.iv_check,item.isSelect()?R.mipmap.icon_select_on:R.mipmap.icon_select_off);
        helper.getView(R.id.iv_minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(tvNum.getText().toString());
                if (num>1){
                    num--;
                    tvNum.setText(String.valueOf(num));
                }
            }
        });
        helper.getView(R.id.iv_plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(tvNum.getText().toString());
                if (num<100){
                    num++;
                    tvNum.setText(String.valueOf(num));
                }
            }
        });
    }

    public void setIsAllCheck(boolean isAllCheck){
        if (Helper.isNotEmpty(getData())){
            for (ShoppingCartData shoppingCartData:getData()) {
                shoppingCartData.setSelect(isAllCheck);
            }
            notifyDataSetChanged();
        }
    }
}




