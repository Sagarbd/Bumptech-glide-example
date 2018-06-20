package com.millionrmaker.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Glide extends AppCompatActivity {
    ListView listView;
    DBhelper dBhelper;
    android.app.FragmentManager fragmentManager;
    android.app.FragmentTransaction fragmentTransaction;
    List<PHOTO> list = new ArrayList<>();
    Fragment1 fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        listView = findViewById(R.id.listview);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        io.reactivex.Observable<List<PHOTO>> observable = api.getPhotodata().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        dBhelper =  OpenHelperManager.getHelper(this, DBhelper.class);
        final RuntimeExceptionDao<PHOTO, Integer> dao = dBhelper.getRuntimeExceptionDao();


        observable.subscribe(new Observer<List<PHOTO>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<PHOTO> photos) {
                list = photos;
                ListAdapter listAdapter = new ListAdapter(Glide.this, list);
                listView.setAdapter(listAdapter);

                for (int i = 0; i < 15; i++) {
                    dao.create(new PHOTO(photos.get(i).getTitle(), photos.get(i).getUrl(), photos.get(i).getThumbnailUrl()));
                    Log.i("GLIDE:-", photos.get(i).getTitle() + " " + photos.get(i).getUrl() + " " + photos.get(i).getThumbnailUrl());
                    Log.i("GLIDE Size:-", Integer.toString(photos.size()));
                }


            }

            @Override
            public void onError(Throwable e) {
                Log.i("GLIDE:-", e.toString());
            }

            @Override
            public void onComplete() {
                Log.i("List Size:-", Integer.toString(list.size()));

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fragment1 = new Fragment1();
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.container, fragment1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });


    }
}
