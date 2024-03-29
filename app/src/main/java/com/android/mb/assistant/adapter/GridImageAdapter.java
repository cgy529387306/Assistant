package com.android.mb.assistant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.mb.assistant.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;


/**
 * author：luck
 * project：PictureSelector
 * package：com.luck.pictureselector.adapter
 * email：893855882@qq.com
 * data：16/7/27
 */
public class GridImageAdapter extends
        RecyclerView.Adapter<GridImageAdapter.ViewHolder> {
    public static final int TYPE_CAMERA = 1;
    public static final int TYPE_PICTURE = 2;

    private LayoutInflater mInflater;
    private List<LocalMedia> mDataList = new ArrayList<>();
    private int mSelectMax = 6;
    private Context mContext;
    /**
     * 点击添加图片跳转
     */
    private onAddPicClickListener mOnAddPicClickListener;

    public interface onAddPicClickListener {
        void onAddPicClick();
        void onImageDelete(String imagePath);
    }

    public GridImageAdapter(Context context, onAddPicClickListener mOnAddPicClickListener) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mOnAddPicClickListener = mOnAddPicClickListener;
    }

    public void setSelectMax(int selectMax) {
        this.mSelectMax = selectMax;
    }

    public void setList(List<LocalMedia> list) {
        this.mDataList = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mIvImage;
        ImageView mIvDelete;

        public ViewHolder(View view) {
            super(view);
            mIvImage = (ImageView) view.findViewById(R.id.iv_image);
            mIvDelete = (ImageView) view.findViewById(R.id.iv_del);
        }
    }

    @Override
    public int getItemCount() {
        if (mDataList.size() < mSelectMax) {
            return mDataList.size() + 1;
        } else {
            return mDataList.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isShowAddItem(position)) {
            return TYPE_CAMERA;
        } else {
            return TYPE_PICTURE;
        }
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_filter_image,
                viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    private boolean isShowAddItem(int position) {
        return position ==  mDataList.size();
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        if (getItemViewType(position) == TYPE_CAMERA) {
            viewHolder.mIvImage.setImageResource(R.mipmap.icon_camera_add);
            viewHolder.mIvImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnAddPicClickListener.onAddPicClick();
                }
            });
            viewHolder.mIvDelete.setVisibility(View.INVISIBLE);
        } else {
            LocalMedia media = mDataList.get(position);
            int mimeType = media.getMimeType();
            String imagePath = media.isCompressed()?media.getCompressPath():media.getPath();
            if (mimeType == PictureMimeType.ofImage()) {
                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .placeholder(R.mipmap.icon_camera_add)
                        .diskCacheStrategy(DiskCacheStrategy.ALL);
                Glide.with(viewHolder.itemView.getContext())
                        .load(imagePath)
                        .apply(options)
                        .into(viewHolder.mIvImage);
            }
            //itemView 的点击事件
            viewHolder.mIvDelete.setVisibility(View.VISIBLE);
            if (mItemClickListener != null) {
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int adapterPosition = viewHolder.getAdapterPosition();
                        mItemClickListener.onItemClick(adapterPosition, v);
                    }
                });
                viewHolder.mIvDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int index = viewHolder.getAdapterPosition();
                        // 这里有时会返回-1造成数据下标越界,具体可参考getAdapterPosition()源码，
                        // 通过源码分析应该是bindViewHolder()暂未绘制完成导致，知道原因的也可联系我~感谢
                        if (index != RecyclerView.NO_POSITION) {
                            mDataList.remove(index);
                            notifyItemRemoved(index);
                            notifyItemRangeChanged(index, mDataList.size());
                            mOnAddPicClickListener.onImageDelete(imagePath);
                        }
                    }
                });
            }
        }
    }

    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
