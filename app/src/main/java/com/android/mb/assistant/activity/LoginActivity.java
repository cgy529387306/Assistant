package com.android.mb.assistant.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.CallSuper;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.base.BaseMvpActivity;
import com.android.mb.assistant.constants.CodeConstants;
import com.android.mb.assistant.constants.ProjectConstants;
import com.android.mb.assistant.entitys.CurrentUser;
import com.android.mb.assistant.entitys.LoginResp;
import com.android.mb.assistant.presenter.CommonPresenter;
import com.android.mb.assistant.utils.Helper;
import com.android.mb.assistant.utils.JsonHelper;
import com.android.mb.assistant.utils.MACHelper;
import com.android.mb.assistant.utils.NavigationHelper;
import com.android.mb.assistant.utils.PreferencesHelper;
import com.android.mb.assistant.utils.ProjectHelper;
import com.android.mb.assistant.view.interfaces.ICommonView;
import com.android.mb.assistant.widget.ClearableEditText;

import java.util.HashMap;
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
        setAccount();
    }

    @Override
    protected void setListener() {
        mTvLogin.setOnClickListener(this);
    }

    private void initView() {
        mTvLogin = findViewById(R.id.tv_login);
        mEtAccount = findViewById(R.id.et_account);
        mEtPwd = findViewById(R.id.et_pwd);
        mEtAccount.setSelection(mEtAccount.getText().length());
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
    public void requestSuccess(String requestCode,String result) {
        LoginResp resp = JsonHelper.fromJson(result,LoginResp.class);
        if (resp!=null){
            if (resp.isSuccess() && resp.getData()!=null){
                saveAccount();
                CurrentUser.getInstance().login(resp.getData());
                showToastMessage("登录成功");
                NavigationHelper.startActivity(this, MainActivity.class,null,true);
            }else{
                showToastMessage(resp.getMessage());
            }
        }
    }

    private void saveAccount(){
        String account = mEtAccount.getText().toString().trim();
        String pwd = mEtPwd.getText().toString().trim();
        PreferencesHelper.getInstance().putString(ProjectConstants.KEY_LOGIN_ACCOUNT,account);
        PreferencesHelper.getInstance().putString(ProjectConstants.KEY_LOGIN_PASSWORD,pwd);
    }

    private void setAccount(){
        String account = PreferencesHelper.getInstance().getString(ProjectConstants.KEY_LOGIN_ACCOUNT);
        String pwd = PreferencesHelper.getInstance().getString(ProjectConstants.KEY_LOGIN_PASSWORD);
        if (Helper.isNotEmpty(account) && Helper.isNotEmpty(pwd)){
            mEtAccount.setText(account);
            mEtAccount.setSelection(account.length());
            mEtPwd.setText(pwd);
            mEtPwd.setSelection(pwd.length());
        }
    }

    /**
     * 点击非编辑区域收起键盘
     * 获取点击事件
     */
    @CallSuper
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() ==  MotionEvent.ACTION_DOWN ) {
            View view = getCurrentFocus();
            if (isShouldHideKeyBord(view, ev)) {
                hideSoftInput(view.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 判定当前是否需要隐藏
     */
    protected boolean isShouldHideKeyBord(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
            return !(ev.getX() > left && ev.getX() < right && ev.getY() > top && ev.getY() < bottom);
        }
        return false;
    }

    /**
     * 隐藏软键盘
     */
    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
