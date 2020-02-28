package com.example.freshdaily;

import android.app.Application;

import com.onesignal.OneSignal;

public class Notification extends Application {

    private static Notification notification;

    public void Notification(){
        notification = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }

    public static synchronized Notification getInstance(){
        return notification;
    }
}
