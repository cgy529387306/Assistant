package com.android.mb.assistant.adapter;

import com.android.mb.assistant.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


/**
 * Created by necer on 2017/6/7.
 */
public class IGoodsManageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public IGoodsManageAdapter(List data) {
        super(R.layout.item_i_goods_manage, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_content,item);

    }
}




