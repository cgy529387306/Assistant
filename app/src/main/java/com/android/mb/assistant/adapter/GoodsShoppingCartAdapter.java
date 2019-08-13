package com.android.mb.assistant.adapter;

import android.view.View;
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.entitys.CartBean;
import com.android.mb.assistant.entitys.GoodsBean;
import com.android.mb.assistant.utils.Helper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


/**
 * Created by necer on 2017/6/7.
 */
public class GoodsShoppingCartAdapter extends BaseQuickAdapter<CartBean, BaseViewHolder> {

    private OnItemOperateListener mItemOperateListener;

    public void setItemOperateListener(OnItemOperateListener itemOperateListener) {
        mItemOperateListener = itemOperateListener;
    }

    public interface OnItemOperateListener{
        void plusNum(CartBean item);
        void minusNum(CartBean item);
    }

    public GoodsShoppingCartAdapter(List data) {
        super(R.layout.item_goods_shopping_cart, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, CartBean item) {
        TextView tvNum = helper.getView(R.id.tv_num);
        helper.setText(R.id.tv_name,item.getMaterialName());
        helper.setText(R.id.tv_price,"￥"+item.getMaterialPrice());
        helper.setText(R.id.tv_time,Helper.long2DateString(item.getTime(),Helper.DATE_FORMAT1));
        helper.setText(R.id.tv_num,String.valueOf(item.getMaterialNum()));
        helper.setImageResource(R.id.iv_check,item.isSelect()?R.mipmap.icon_select_on:R.mipmap.icon_select_off);
        helper.getView(R.id.iv_minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(tvNum.getText().toString());
                if (num>1){
                    num--;
                    tvNum.setText(String.valueOf(num));
                    if (mItemOperateListener!=null){
                        mItemOperateListener.minusNum(item);
                    }
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
                    if (mItemOperateListener!=null){
                        mItemOperateListener.plusNum(item);
                    }
                }
            }
        });
    }

    public void setIsAllCheck(boolean isAllCheck){
        if (Helper.isNotEmpty(getData())){
            for (CartBean cartBean:getData()) {
                cartBean.setSelect(isAllCheck);
            }
            notifyDataSetChanged();
        }
    }
}




