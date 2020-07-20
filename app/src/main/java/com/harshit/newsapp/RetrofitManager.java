package com.harshit.newsapp;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class RetrofitManager {
    String URL = "https://newsapi.org/v2/";

    OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS);

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.client(httpClient.build()).build();

    public interface RetrofitApi {
        @GET("everything?q=bitcoin&sortBy=publishedAt&apiKey=472194e741f64db691a9cc21a70a9370")
        Call<ResponseAPI> getNews();
    }

    RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);

    public MutableLiveData<ResponseAPI> getNews() {
        final MutableLiveData<ResponseAPI> newsData = new MutableLiveData<>();
        Call<ResponseAPI> call = retrofitApi.getNews();
        call.enqueue(new Callback<ResponseAPI>() {

            @Override
            public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                if (response.isSuccessful()) {
                    Log.v("RetrofitManagr","Done");
                    newsData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseAPI> call, Throwable t) {
                Log.v("RetrofitManager","something went wrong");
            }
        });
        return newsData;
    }
}
