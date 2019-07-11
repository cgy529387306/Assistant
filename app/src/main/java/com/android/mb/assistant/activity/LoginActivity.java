package com.android.mb.assistant.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.base.BaseMvpActivity;
import com.android.mb.assistant.constants.CodeConstants;
import com.android.mb.assistant.entitys.CurrentUser;
import com.android.mb.assistant.presenter.CommonPresenter;
import com.android.mb.assistant.entitys.LoginResp;
import com.android.mb.assistant.utils.Helper;
import com.android.mb.assistant.utils.JsonHelper;
import com.android.mb.assistant.utils.MACHelper;
import com.android.mb.assistant.utils.NavigationHelper;
import com.android.mb.assistant.utils.ProjectHelper;
import com.android.mb.assistant.view.interfaces.ICommonView;
import com.android.mb.assistant.widget.ClearableEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends BaseMvpActivity<CommonPresenter, ICommonView> implements ICommonView, View.OnClickListener {

    private TextView mTvLogin;
    private ClearableEditText mEtAccount;
    private ClearableEditText mEtPwd;
    @Override
    protected void loadIntent() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initTitle() {
        hideActionBar();
    }

    @Override
    protected void bindViews() {
        initView();
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {
        mTvLogin.setOnClickListener(this);
    }

    private void initView() {
        mTvLogin = findViewById(R.id.tv_login);
        mEtAccount = findViewById(R.id.et_account);
        mEtPwd = findViewById(R.id.et_pwd);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_login){
            ProjectHelper.disableViewDoubleClick(view);
            doLogin();
        }
    }

    private void doLogin() {
        String account = mEtAccount.getText().toString().trim();
        String pwd = mEtPwd.getText().toString().trim();
        if (Helper.isEmpty(account)){
            showToastMessage("请输入用户名");
            return;
        }
        if (Helper.isEmpty(pwd)){
            showToastMessage("请输入密码");
            return;
        }
        Map<String,String> requestParams = new HashMap<>();
        requestParams.put("userName",account);
        requestParams.put("userPwd",MACHelper.pwd(pwd));
        mPresenter.requestData(CodeConstants.KEY_LOGIN,requestParams,true);
    }


    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter();
    }

    @Override
    public void requestSuccess(String result) {
        LoginResp resp = JsonHelper.fromJson(result,LoginResp.class);
        if (resp!=null){
            if (resp.isSuccess() && resp.getData()!=null){
                CurrentUser.getInstance().login(resp.getData());
                showToastMessage("登录成功");
                NavigationHelper.startActivity(this, MainActivity.class,null,true);
            }else{
                showToastMessage(resp.getMessage());
            }
        }
    }
}
