package com.android.mb.assistant.activity.goods;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.activity.competitive.CompetitiveDetailsActivity;
import com.android.mb.assistant.adapter.MyFragmentPagerAdapter;
import com.android.mb.assistant.base.BaseActivity;
import com.android.mb.assistant.base.BaseMvpActivity;
import com.android.mb.assistant.constants.CodeConstants;
import com.android.mb.assistant.entitys.CommonResp;
import com.android.mb.assistant.entitys.CompetitiveBean;
import com.android.mb.assistant.entitys.CurrentUser;
import com.android.mb.assistant.entitys.GoodsBean;
import com.android.mb.assistant.fragment.GoodsBrowseFragment;
import com.android.mb.assistant.presenter.CommonPresenter;
import com.android.mb.assistant.utils.Helper;
import com.android.mb.assistant.utils.ImageUtils;
import com.android.mb.assistant.utils.JsonHelper;
import com.android.mb.assistant.utils.NavigationHelper;
import com.android.mb.assistant.utils.ProjectHelper;
import com.android.mb.assistant.view.interfaces.ICommonView;
import com.android.mb.assistant.widget.FragmentViewPager;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 物资详情
 */
public class GoodsDetailsActivity extends BaseMvpActivity<CommonPresenter, ICommonView> implements ICommonView, View.OnClickListener{

    private GoodsBean mGoodsBean;
    private Banner mBanner;
    private TextView mTvName,mTvModel,mTvDepartment;
    private TextView mTvCategory,mTvStorageSite,mTvNumber,mTvPrice;
    private TextView mTvIsAsset,mTvGsDepartment,mTvInputTime,mTvContacts;
    private List<String> mImageList = new ArrayList<>();
    private ImageView mIvMinus,mIvPlus;
    private TextView mTvNum,mTvApply;
    @Override
    protected void loadIntent() {
        mGoodsBean = (GoodsBean) getIntent().getSerializableExtra("goods");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_details;
    }

    @Override
    protected void initTitle() {
        setTitleText("物资详情");
    }

    @Override
    protected void bindViews() {
        mBanner = findViewById(R.id.bannerView);
        mTvName = findViewById(R.id.tv_name);
        mTvModel = findViewById(R.id.tv_model);
        mTvDepartment = findViewById(R.id.tv_department);
        mTvCategory = findViewById(R.id.tv_category);
        mTvStorageSite = findViewById(R.id.tv_storage_site);
        mTvNumber = findViewById(R.id.tv_number);
        mTvPrice = findViewById(R.id.tv_price);
        mTvIsAsset = findViewById(R.id.tv_is_asset);
        mTvGsDepartment = findViewById(R.id.tv_gs_department);
        mTvInputTime = findViewById(R.id.tv_input_time);
        mTvContacts = findViewById(R.id.tv_contacts);
        mIvMinus = findViewById(R.id.iv_minus);
        mIvPlus = findViewById(R.id.iv_plus);
        mTvNum = findViewById(R.id.tv_num);
        mTvApply = findViewById(R.id.tv_apply);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        initData();
    }

    private void initData(){
        if (mGoodsBean!=null){
            mTvName.setText(mGoodsBean.getMaterialName());
            mTvModel.setText(mGoodsBean.getUnitname());
            mTvDepartment.setText(mGoodsBean.getGsDepartment());
            mTvCategory.setText(mGoodsBean.getGsDepartment());
            mTvStorageSite.setText(mGoodsBean.getStorageSite());
            mTvNumber.setText(String.valueOf(mGoodsBean.getMum()));
            mTvIsAsset.setText(mGoodsBean.getAsset()==0?"是":"否");
            mTvGsDepartment.setText(mGoodsBean.getGsDepartment());
            mTvInputTime.setText(Helper.long2DateString(mGoodsBean.getCreateTime(),Helper.DATE_FORMAT1));
            mTvContacts.setText(mGoodsBean.getContacts());
            mTvPrice.setText(mGoodsBean.getPrice());
            mTvNum.setText(String.valueOf(mGoodsBean.getAddMum()));
            mImageList = ProjectHelper.strToList(mGoodsBean.getImg());
            mBanner.setImageLoader(new GlideImageLoader());
            mBanner.setImages(mImageList);
            mBanner.start();
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_plus){
            int num = Integer.parseInt(mTvNum.getText().toString());
            if (num<100){
                num++;
                mTvNum.setText(String.valueOf(num));
                mGoodsBean.setAddMum(num);
            }
        }else if (id == R.id.iv_minus){
            int num = Integer.parseInt(mTvNum.getText().toString());
            if (num>1){
                num--;
                mTvNum.setText(String.valueOf(num));
                mGoodsBean.setAddMum(num);
            }
        }else if (id == R.id.tv_apply){
            doApply(mGoodsBean);
        }
    }

    public void doApply(GoodsBean item) {
        Map<String,String> requestParams = new HashMap<>();
        requestParams.put("materialId",item.getMaterialId());
        requestParams.put("mUid", CurrentUser.getInstance().getMuid());
        requestParams.put("materialNum",String.valueOf(item.getAddMum()));
        mPresenter.requestCart(CodeConstants.KEY_CART_ADD,requestParams,false);
    }

    @Override
    public void requestSuccess(String requestCode, String result) {
        if (CodeConstants.KEY_CART_ADD.equals(requestCode)){
            CommonResp resp = JsonHelper.fromJson(result,CommonResp.class);
            if (resp!=null){
                if (resp.isSuccess()){
                    showToastMessage("添加购物车成功!");
                    NavigationHelper.startActivity(GoodsDetailsActivity.this, GoodsShoppingCartActivity.class,null,false);
                }else{
                    showToastMessage(resp.getMessage());
                }
            }
        }
    }

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter();
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            ImageUtils.displayImage(GoodsDetailsActivity.this,(String)path,imageView);
        }
    }

    @Override
    protected void setListener() {
        mIvMinus.setOnClickListener(this);
        mIvPlus.setOnClickListener(this);
        mTvApply.setOnClickListener(this);
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (mImageList.size() > 0 && mImageList.size()>position) {
                    List<LocalMedia> dataList = new ArrayList<>();
                    for (String imageUrl:mImageList){
                        LocalMedia media = new LocalMedia();
                        media.setPath(imageUrl);
                        media.setCompressed(false);
                        media.setPictureType("image/jpeg");
                        dataList.add(media);
                    }
                    PictureSelector.create(GoodsDetailsActivity.this).externalPicturePreview(position, dataList);
                }
            }
        });
    }

}
