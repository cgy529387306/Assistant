package com.android.mb.assistant.activity.goods;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.adapter.GoodsApplyUseAdapter;
import com.android.mb.assistant.adapter.MyFragmentPagerAdapter;
import com.android.mb.assistant.base.BaseActivity;
import com.android.mb.assistant.fragment.ChapterApprovalFragment;
import com.android.mb.assistant.widget.FragmentViewPager;
import com.android.mb.assistant.widget.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

/**
 * 用章审批详情
 */
public class ChapterApprovalDeailsActivity extends BaseActivity implements View.OnClickListener{

    private TextView mTvName,mTvTel,mTvTime,mTvRemark;
    private TextView mTvReturn,mTvRefuse,mTvAgree,mTvRevoke;
    private LinearLayout mLlOperate;
    private RecyclerView mRecyclerView;
    private GoodsApplyUseAdapter mAdapter;
    private int mType;
    @Override
    protected void loadIntent() {
        mType = getIntent().getIntExtra("type",0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chapter_approval_details;
    }

    @Override
    protected void initTitle() {
        setTitleText(mType==1||mType==2?"我的使用申请":"使用申请审批");
    }

    @Override
    protected void bindViews() {
        mTvName = findViewById(R.id.tv_name);
        mTvTel = findViewById(R.id.tv_tel);
        mTvTime = findViewById(R.id.tv_time);
        mTvRemark = findViewById(R.id.tv_remark);
        mTvReturn = findViewById(R.id.tv_return);
        mTvRefuse = findViewById(R.id.tv_refuse);
        mTvAgree = findViewById(R.id.tv_agree);
        mTvRevoke = findViewById(R.id.tv_revoke);
        mLlOperate = findViewById(R.id.lly_operate);
        mTvRevoke.setVisibility(mType==1||mType==2?View.VISIBLE:View.GONE);
        mLlOperate.setVisibility(mType==1||mType==2?View.GONE:View.VISIBLE);
        mRecyclerView = findViewById(R.id.rv_goods);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(LinearLayoutManager.VERTICAL,1,getResources().getColor(R.color.gray_divider)));
        mAdapter = new GoodsApplyUseAdapter(getList());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {
        mTvReturn.setOnClickListener(this);
        mTvRefuse.setOnClickListener(this);
        mTvAgree.setOnClickListener(this);
        mTvRevoke.setOnClickListener(this);
    }

    private List<String> getList(){
        List<String> list = new ArrayList<>();
        list.add("苹果电脑");
        list.add("华硕电脑");
        return list;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_return){
            //退回
        }else if (id == R.id.tv_refuse){
            //拒绝
        }else if (id == R.id.tv_agree){
            //同意
        }else if (id == R.id.tv_revoke){
            //撤回
        }
    }
}
