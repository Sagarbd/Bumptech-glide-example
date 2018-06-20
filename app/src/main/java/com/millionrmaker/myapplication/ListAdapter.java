package com.millionrmaker.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ListAdapter extends BaseAdapter {

    List<PHOTO> list;
    Context context;
    LayoutInflater inflater;

    ListAdapter(Context context, List<PHOTO> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(this.context);
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyViewHolder mViewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.custom_layout, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }
        Log.i("GLIDE Size:-",Integer.toString(list.get(position).getId()));
        mViewHolder.id.setText(Integer.toString(position));
        mViewHolder.title.setText(list.get(position).getTitle());
        Glide.with(context)
                .load(list.get(position).getThumbnailUrl())
                .into(mViewHolder.imageView);

        return convertView;
    }

    private class MyViewHolder {
        TextView id, title;
        LinearLayout linearLayout;
        ImageView imageView;

        public MyViewHolder(View item) {
            imageView = item.findViewById(R.id.small_image);
            id = (TextView) item.findViewById(R.id.id);
            title = (TextView) item.findViewById(R.id.title);
            linearLayout = (LinearLayout) item.findViewById(R.id.linearcustome);

        }
    }
}
