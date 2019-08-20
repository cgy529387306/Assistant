package com.android.mb.assistant.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.mb.assistant.R;
import com.android.mb.assistant.activity.goods.GoodsDetailsActivity;
import com.android.mb.assistant.entitys.ApplyBean;
import com.android.mb.assistant.entitys.GoodsManage;
import com.android.mb.assistant.utils.NavigationHelper;
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
        void onGoodsApproval(int type,ApplyBean applyBean);
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
        RecyclerView recyclerView = helper.getView(R.id.rv_goods);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new RecycleViewDivider(LinearLayoutManager.VERTICAL,1,mContext.getResources().getColor(R.color.gray_divider)));
        if (helper.getAdapterPosition()==3){
            GoodsItemAdapter itemAdapter = new GoodsItemAdapter(item.getGoodsList());
            recyclerView.setAdapter(itemAdapter);
            itemAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("goods",itemAdapter.getItem(position));
                    NavigationHelper.startActivity((Activity) mContext, GoodsDetailsActivity.class,bundle,false);
                }
            });
        }else{
            IGoodsManageAdapter goodsManageAdapter = new IGoodsManageAdapter(item.getDataList());
            recyclerView.setAdapter(goodsManageAdapter);
            goodsManageAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    mOnChapterApprovalListener.onGoodsApproval(helper.getAdapterPosition()==1?0:1,goodsManageAdapter.getItem(position));
                }
            });
        }
    }


}




