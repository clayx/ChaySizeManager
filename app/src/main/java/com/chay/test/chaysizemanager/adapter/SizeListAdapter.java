package com.chay.test.chaysizemanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chay.test.chaysizemanager.R;
import com.chay.test.chaysizemanager.info.SizeListInfo;
import com.chay.test.chaysizemanager.otherView.RoundImageView;
import com.chay.test.chaysizemanager.util.Preconditions;

import java.util.List;


/**
 * Created by Chay on 2016/11/9.
 *
 * @Description:尺码助手的Adapter
 */

public class SizeListAdapter extends BaseAdapter {

    private Context context;
    private List<SizeListInfo.UserSizeInfo> list;
    private LayoutInflater inflater;

    public SizeListAdapter(Context context, List<SizeListInfo.UserSizeInfo> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SizeViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.size_list_item_layout, null);
            holder = new SizeViewHolder();
            holder.size_list_item_round_iv = (RoundImageView) convertView.findViewById(R.id.size_list_item_round_iv);
            holder.size_list_item_name_tv = (TextView) convertView.findViewById(R.id.size_list_item_name_tv);
            holder.size_list_item_right_iv = (ImageView) convertView.findViewById(R.id.size_list_item_right_iv);
            holder.size_list_item_rl = (RelativeLayout) convertView.findViewById(R.id.size_list_item_rl);
            convertView.setTag(holder);
        } else {
            holder = (SizeViewHolder) convertView.getTag();
        }
        holder.size_list_item_name_tv.setText(Preconditions.nullToEmpty(list.get(position).getFullName()));
        holder.size_list_item_round_iv.setTag(list.get(position).getId());
        return convertView;
    }

    public class SizeViewHolder {
        private RoundImageView size_list_item_round_iv;
        private TextView size_list_item_name_tv;
        private ImageView size_list_item_right_iv;
        private RelativeLayout size_list_item_rl;
    }
}
