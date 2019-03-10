package com.rasa.loyality;

import android.app.Activity;
import android.content.Intent;

import com.rasa.loyality.sendCode.SendCodeActivity;

public class Loyalty {
    public static void startLoyalty (Activity activity,int requestCode){
        activity.startActivityForResult(new Intent(activity, SendCodeActivity.class),requestCode);
    }

}
