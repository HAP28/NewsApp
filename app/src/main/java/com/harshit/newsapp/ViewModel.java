package com.harshit.newsapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

public class ViewModel extends AndroidViewModel {
    private Application application;
    private RetrofitManager retrofitManager = new RetrofitManager();
    private MediatorLiveData<ResponseAPI> responseAPIMediatorLiveData = new MediatorLiveData<>();

    public ViewModel(Application application) {
        super(application);
        this.application = application;
    }

    public LiveData<ResponseAPI> News() {
        return responseAPIMediatorLiveData;
    }
    public void getNews() {
        responseAPIMediatorLiveData.addSource(retrofitManager.getNews(), responseAPI -> {
            if(responseAPI != null) {
                responseAPIMediatorLiveData.setValue(responseAPI);
            }
        });
    }

}
