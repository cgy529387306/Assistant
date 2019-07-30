package com.android.mb.assistant.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.activity.competitive.SelectPersonActivity;
import com.android.mb.assistant.entitys.CompetitiveBean;
import com.android.mb.assistant.utils.Helper;
import com.android.mb.assistant.utils.NavigationHelper;
import com.android.mb.assistant.utils.ProjectHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


/**
 * Created by necer on 2017/6/7.
 */
public class CompetitiveOrderAdapter extends BaseQuickAdapter<CompetitiveBean, BaseViewHolder> {

    public CompetitiveOrderAdapter(List data) {
        super(R.layout.item_competitive_order, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, CompetitiveBean item) {
        String yys = item.getCIsp()==0?"电信":"联通";
        String dqs = Helper.long2DateString(item.getCBecomeTime(),Helper.DATE_FORMAT1);
        String kd = yys+"-"+dqs+"到期";
        String yfg = item.getCIsOverlap()==0?"已覆盖":"未覆盖";
        String yw = item.getCIsBroadband()==0?"异网":"未异网";
        String ywfg = yfg +"-"+yw;
        helper.setText(R.id.tv_order_state, ProjectHelper.getOrderStatus(item));
        helper.setText(R.id.tv_order_time, Helper.long2DateString(item.getCCreateTime(),Helper.DATE_FORMAT1));
        helper.setText(R.id.tv_name,item.getCUsername());
        helper.setText(R.id.tv_kd,kd);
        helper.setText(R.id.tv_tel,item.getCMobile());
        helper.setText(R.id.tv_address,item.getCAdd());
        helper.setText(R.id.tv_ywfg,ywfg);
        TextView tvDispatch = helper.getView(R.id.tv_dispatch);
        tvDispatch.setVisibility(item.getCDispatchStatus()==1? View.VISIBLE:View.GONE);
        tvDispatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("competitiveId",item.getCId());
                NavigationHelper.startActivity((Activity) mContext, SelectPersonActivity.class,bundle,false);
            }
        });
    }
}




