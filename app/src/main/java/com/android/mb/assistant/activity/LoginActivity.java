package com.android.mb.assistant.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.base.BaseMvpActivity;
import com.android.mb.assistant.constants.ProjectConstants;
import com.android.mb.assistant.entitys.CurrentUser;
import com.android.mb.assistant.entitys.UserBean;
import com.android.mb.assistant.presenter.LoginPresenter;
import com.android.mb.assistant.utils.Helper;
import com.android.mb.assistant.utils.MACHelper;
import com.android.mb.assistant.utils.NavigationHelper;
import com.android.mb.assistant.utils.PreferencesHelper;
import com.android.mb.assistant.utils.ProjectHelper;
import com.android.mb.assistant.view.interfaces.ILoginView;
import com.android.mb.assistant.widget.ClearableEditText;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseMvpActivity<LoginPresenter, ILoginView> implements ILoginView, View.OnClickListener {

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
        try {
            String data = account + MACHelper.KEY_SPLIT + MACHelper.pwd(pwd);
            Map<String,Object> requestMap = new HashMap<>();
            requestMap.put("code","100001");
            requestMap.put("data",data);
            requestMap.put("mac",MACHelper.workMacForApp(data));
            mPresenter.userLogin(requestMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loginSuccess(UserBean result) {
        if (result!=null){
            CurrentUser.getInstance().login(result);
            showToastMessage("登录成功");
            NavigationHelper.startActivity(this, MainActivity.class,null,true);
        }
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }
}
