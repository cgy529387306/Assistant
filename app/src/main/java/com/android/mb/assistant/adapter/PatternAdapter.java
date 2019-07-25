package com.android.mb.assistant.adapter;

import com.android.mb.assistant.R;
import com.android.mb.assistant.entitys.CityBean;
import com.android.mb.assistant.entitys.PatternBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


/**
 * Created by necer on 2017/6/7.
 */
public class PatternAdapter extends BaseQuickAdapter<PatternBean, BaseViewHolder> {

    public PatternAdapter(List data) {
        super(R.layout.item_person, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, PatternBean item) {
        helper.setText(R.id.tv_user_name,item.getPatternName());
    }



}




