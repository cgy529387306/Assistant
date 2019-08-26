package com.android.mb.assistant.adapter;

import com.android.mb.assistant.R;
import com.android.mb.assistant.entitys.NoticeBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


/**
 * Created by necer on 2017/6/7.
 */
public class HomeMsgAdapter extends BaseQuickAdapter<NoticeBean, BaseViewHolder> {

    public HomeMsgAdapter(List data) {
        super(R.layout.item_home_msg, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, NoticeBean item) {
        helper.setText(R.id.tv_title,item.getMtitle());
        helper.setText(R.id.tv_time,item.getMcratetime());
        helper.setText(R.id.tv_content,item.getMcontent());
    }
}




