package com.android.mb.assistant.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.base.BaseMvpActivity;
import com.android.mb.assistant.presenter.LoginPresenter;
import com.android.mb.assistant.utils.NavigationHelper;
import com.android.mb.assistant.utils.ProjectHelper;
import com.android.mb.assistant.view.interfaces.ILoginView;

public class LoginActivity extends BaseMvpActivity<LoginPresenter, ILoginView> implements ILoginView, View.OnClickListener {

    private TextView mTvLogin;
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
        NavigationHelper.startActivity(LoginActivity.this,MainActivity.class,null,true);
    }

    @Override
    public void loginSuccess(Object result) {

    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }
}
