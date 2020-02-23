package com.example.freshdaily.ui.Logout;

import android.content.Intent;

import com.example.freshdaily.SignIn;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RateOurAppViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RateOurAppViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Logout");

    }


    public LiveData<String> getText() {
        return mText;
    }
}