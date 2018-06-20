package com.millionrmaker.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

public class Fragment1 extends Fragment {
List<PHOTO> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment,container,false);
        ViewPager viewPager = view.findViewById(R.id.viewpager);


        DBhelper dBhelper = (DBhelper) OpenHelperManager.getHelper(getActivity(),DBhelper.class);
        RuntimeExceptionDao<PHOTO,Integer> dao = dBhelper.getRuntimeExceptionDao();
        list = dao.queryForAll();


        ViewpagerAdapter viewpagerAdapter = new ViewpagerAdapter(getActivity(),list);

        viewPager.setAdapter(viewpagerAdapter);
        return view;
    }
}
