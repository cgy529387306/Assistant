package com.android.mb.assistant.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.entitys.GoodsBean;
import com.android.mb.assistant.utils.Helper;
import com.android.mb.assistant.utils.ImageUtils;
import com.android.mb.assistant.utils.ProjectHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


/**
 * Created by necer on 2017/6/7.
 */
public class GoodsBrowseAdapter extends BaseQuickAdapter<GoodsBean, BaseViewHolder> {

    private OnItemOperateListener mItemOperateListener;

    public void setItemOperateListener(OnItemOperateListener itemOperateListener) {
        mItemOperateListener = itemOperateListener;
    }

    public interface OnItemOperateListener{
        void onApply(GoodsBean item);
    }

    public GoodsBrowseAdapter(List data) {
        super(R.layout.item_goods_browse, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, GoodsBean item) {
        TextView tvNum = helper.getView(R.id.tv_num);
        ImageView imageView = helper.getView(R.id.iv_goods);
        helper.setText(R.id.tv_name,item.getMaterialName());
        helper.setText(R.id.tv_department,item.getGsDepartment());
        helper.setText(R.id.tv_input_time, Helper.long2DateString(item.getCreateTime(),Helper.DATE_FORMAT1));
        helper.setText(R.id.tv_price,item.getUnitname());
        if (Helper.isNotEmpty(item.getImg())){
            List<String> imageList = ProjectHelper.strToList(item.getImg());
            if (Helper.isNotEmpty(imageList)){
                ImageUtils.displayImage(mContext,imageList.get(0),imageView);
            }
        }
        helper.getView(R.id.iv_minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(tvNum.getText().toString());
                if (num>0){
                    num--;
                    tvNum.setText(String.valueOf(num));
                }
            }
        });
        helper.getView(R.id.iv_plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(tvNum.getText().toString());
                if (num<100){
                    num++;
                    tvNum.setText(String.valueOf(num));
                }
            }
        });
        helper.getView(R.id.tv_apply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemOperateListener!=null){
                    mItemOperateListener.onApply(item);
                }
            }
        });

    }
}




