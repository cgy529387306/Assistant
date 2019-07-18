package com.android.mb.assistant.adapter;

import android.widget.EditText;

import com.android.mb.assistant.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


/**
 * Created by necer on 2017/6/7.
 */
public class CompetitiveTelAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public CompetitiveTelAdapter(List data) {
        super(R.layout.item_competitive_tel, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {
        final int position = helper.getAdapterPosition();
        EditText etInput = helper.getView(R.id.et_tel_number);
        etInput.setText(item==null?"":item);
        etInput.setSelection(item==null?0:item.length());
        helper.setText(R.id.tv_tel_name,"异网号码"+(position+1));
    }



}




