package com.android.mb.assistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mb.assistant.R;
import com.android.mb.assistant.entitys.IType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/6/24.
 */
public class ITypeAdapter extends BaseAdapter {
    private Context mContext;
    public List<IType> mData = new ArrayList<IType>();
    public ITypeAdapter(Context context, List<IType> htList) {
        mContext = context;
        mData = htList;
    }

    public int getCount() {
        return mData==null?0:mData.size();
    }

    public boolean areAllItemsEnabled() {
        return false;
    }

    public IType getItem(int position) {
        return mData.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_home_type, null);
            viewHolder = new ViewHolder();
            viewHolder.ivCover = convertView.findViewById(R.id.iv_icon);
            viewHolder.tvTitle = convertView.findViewById(R.id.tv_home_type);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final IType homeType = mData.get(position);
        viewHolder.tvTitle.setText(homeType.getName());
        viewHolder.ivCover.setImageResource(homeType.getDrawableId());
        return convertView;
    }

    private class ViewHolder {
        public ImageView ivCover;
        public TextView tvTitle;
    }

}
