package com.android.mb.assistant.adapter;

import android.widget.ImageView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.utils.ImageUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class ImageListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ImageListAdapter(List data) {
        super(R.layout.item_image, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView imageView = helper.getView(R.id.iv_image);
        ImageUtils.displayImage(mContext,item,imageView);
    }

}
