package com.millionrmaker.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

class ViewpagerAdapter extends PagerAdapter{
    Context context;
    List<PHOTO> list;
    ImageView imageView;
    TextView id,title;
    public ViewpagerAdapter(Context context, List<PHOTO> list) {
     this.context = context;
     this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_viewpager_layout,container,false);
        imageView = view.findViewById(R.id.big_image);
        Log.i("sasasas",list.get(position).getTitle());
        id = view.findViewById(R.id.id);
        title = view.findViewById(R.id.title);
        id.setText(Integer.toString(position));
        title.setText(list.get(position).getTitle());
        Glide.with(view)
                .load(list.get(position).getUrl())
                .into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
