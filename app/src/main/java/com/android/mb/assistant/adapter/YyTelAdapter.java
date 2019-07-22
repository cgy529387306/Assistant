package com.android.mb.assistant.adapter;

import com.android.mb.assistant.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class YyTelAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public YyTelAdapter(List data) {
        super(R.layout.item_yy_tel, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        final int position = helper.getAdapterPosition();
        helper.setText(R.id.tv_tel_name,"异网号码"+(position+1));
        helper.setText(R.id.tv_tel,item);
    }
}
