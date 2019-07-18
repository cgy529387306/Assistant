package com.android.mb.assistant.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.android.mb.assistant.R;
import com.android.mb.assistant.entitys.CompetitiveBean;
import com.android.mb.assistant.utils.Helper;
import com.android.mb.assistant.utils.ProjectHelper;
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
        helper.setText(R.id.tv_tel_name,"异网号码"+(position+1));
        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                getData().set(position,etInput.getText().toString());
            }
        });
    }



}




