package com.android.mb.assistant.adapter;

import com.android.mb.assistant.R;
import com.android.mb.assistant.entitys.CityBean;
import com.android.mb.assistant.entitys.DicBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


/**
 * Created by necer on 2017/6/7.
 */
public class DicAdapter extends BaseQuickAdapter<DicBean, BaseViewHolder> {

    public DicAdapter(List data) {
        super(R.layout.item_person, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, DicBean item) {
        helper.setText(R.id.tv_user_name,item.getDictname());
    }



}




