package com.android.mb.assistant.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.mb.assistant.R;
import com.android.mb.assistant.widget.RecycleViewDivider;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by necer on 2017/6/7.
 */
public class GoodsManageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private OnChapterApprovalListener mOnChapterApprovalListener;
    public GoodsManageAdapter(List data,OnChapterApprovalListener mOnChapterApprovalListener) {
        super(R.layout.item_goods_manage, data);
        this.mOnChapterApprovalListener = mOnChapterApprovalListener;
    }

    public interface OnChapterApprovalListener{   //用章审批
        void onGoodsApproval();
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_name,item);
        RecyclerView mRecyclerView = helper.getView(R.id.rv_goods);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(LinearLayoutManager.VERTICAL,1,mContext.getResources().getColor(R.color.gray_divider)));
        IGoodsManageAdapter mIGoodsmanageAdapter = new IGoodsManageAdapter(new ArrayList());
        mRecyclerView.setAdapter(mIGoodsmanageAdapter);
        mIGoodsmanageAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mOnChapterApprovalListener.onGoodsApproval();
            }
        });
    }


}




