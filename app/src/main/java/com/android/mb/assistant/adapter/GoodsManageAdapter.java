package com.android.mb.assistant.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.mb.assistant.R;
import com.android.mb.assistant.entitys.ApplyBean;
import com.android.mb.assistant.entitys.GoodsManage;
import com.android.mb.assistant.widget.RecycleViewDivider;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by necer on 2017/6/7.
 */
public class GoodsManageAdapter extends BaseQuickAdapter<GoodsManage, BaseViewHolder> {

    private OnChapterApprovalListener mOnChapterApprovalListener;
    public GoodsManageAdapter(List data,OnChapterApprovalListener mOnChapterApprovalListener) {
        super(R.layout.item_goods_manage, data);
        this.mOnChapterApprovalListener = mOnChapterApprovalListener;
    }

    public interface OnChapterApprovalListener{   //用章审批
        void onGoodsApproval(ApplyBean applyBean);
    }


    @Override
    protected void convert(BaseViewHolder helper, GoodsManage item) {
        helper.setText(R.id.tv_name,item.getName());
        if (helper.getAdapterPosition()==1){
            helper.setImageResource(R.id.iv_icon,R.mipmap.icon_shenpi);
        }else if (helper.getAdapterPosition()==2){
            helper.setImageResource(R.id.iv_icon,R.mipmap.icon_wodesq);
        }else{
            helper.setImageResource(R.id.iv_icon,R.mipmap.icon_zuixin);
        }
        RecyclerView mRecyclerView = helper.getView(R.id.rv_goods);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(LinearLayoutManager.VERTICAL,1,mContext.getResources().getColor(R.color.gray_divider)));
        IGoodsManageAdapter goodsManageAdapter = new IGoodsManageAdapter(item.getDataList());
        mRecyclerView.setAdapter(goodsManageAdapter);
        goodsManageAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mOnChapterApprovalListener.onGoodsApproval(goodsManageAdapter.getItem(position));
            }
        });
    }


}




