package com.android.mb.assistant.adapter;

import com.android.mb.assistant.R;
import com.android.mb.assistant.entitys.GoodsBean;
import com.android.mb.assistant.utils.Helper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


/**
 * Created by necer on 2017/6/7.
 */
public class GoodsItemAdapter extends BaseQuickAdapter<GoodsBean, BaseViewHolder> {

    private OnItemOperateListener mItemOperateListener;

    public void setItemOperateListener(OnItemOperateListener itemOperateListener) {
        mItemOperateListener = itemOperateListener;
    }

    public interface OnItemOperateListener{
        void onApply(GoodsBean item);
    }

    public GoodsItemAdapter(List data) {
        super(R.layout.item_goods_sample, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, GoodsBean item) {
        helper.setText(R.id.tv_name,item.getMaterialName());
        helper.setText(R.id.tv_department,item.getGsDepartment());
        helper.setText(R.id.tv_input_time, Helper.long2DateString(item.getCreateTime(),Helper.DATE_FORMAT1));
    }
}




