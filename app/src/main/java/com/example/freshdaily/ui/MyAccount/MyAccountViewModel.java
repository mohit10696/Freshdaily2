package com.example.freshdaily.ui.MyAccount;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyAccountViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MyAccountViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is MyAccount fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }


}