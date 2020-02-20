package com.example.freshdaily.ui.RateOurApp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RateOurAppViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RateOurAppViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Rate our app fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}