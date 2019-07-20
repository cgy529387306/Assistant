package com.android.mb.assistant.adapter;

import com.android.mb.assistant.R;
import com.android.mb.assistant.entitys.UserBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


/**
 * Created by necer on 2017/6/7.
 */
public class PersonAdapter extends BaseQuickAdapter<UserBean, BaseViewHolder> {

    public PersonAdapter(List data) {
        super(R.layout.item_person, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, UserBean item) {
        helper.setText(R.id.tv_user_name,item.getUname());
    }



}




