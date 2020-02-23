package com.example.freshdaily.ui.RateOurApp1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShopByCategoryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ShopByCategoryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is ShopByCategory fragment1");
    }

    public LiveData<String> getText() {
        return mText;
    }
}