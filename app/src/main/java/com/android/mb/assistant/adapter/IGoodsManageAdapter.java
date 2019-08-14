package com.android.mb.assistant.adapter;

import com.android.mb.assistant.R;
import com.android.mb.assistant.entitys.ApplyBean;
import com.android.mb.assistant.utils.Helper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


/**
 * Created by necer on 2017/6/7.
 */
public class IGoodsManageAdapter extends BaseQuickAdapter<ApplyBean, BaseViewHolder> {

    public IGoodsManageAdapter(List data) {
        super(R.layout.item_i_goods_manage, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, ApplyBean item) {
        helper.setText(R.id.tv_content,item.getApSqName()+"的物资申请");
//        helper.setText(R.id.tv_time, Helper.long2DateString(item.getApTime(),Helper.DATE_FORMAT1));
    }
}




