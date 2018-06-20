package com.millionrmaker.myapplication;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Api {

    public String BASE_URL = "https://jsonplaceholder.typicode.com/";

    @GET("photos")
    Observable<List<PHOTO>>   getPhotodata();
}
