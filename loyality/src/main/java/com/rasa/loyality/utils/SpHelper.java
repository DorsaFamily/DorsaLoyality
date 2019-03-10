package com.rasa.loyality.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SpHelper {


    private static final String KEY_LOYALTY="KEY_LOYALTY";
    private static final String KEY_LOYALTY_CODE="KEY_LOYALTY";

    private Context context;

    public SpHelper(Context context) {
        this.context = context;
    }

    public void setLoyaltyCode(String code) {
        SharedPreferences settings = context.getSharedPreferences(KEY_LOYALTY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(KEY_LOYALTY_CODE, code);
        editor.apply();
    }

    public String getLoyaltyCode(String defeult) {
        SharedPreferences settings = context.getSharedPreferences(KEY_LOYALTY, Context.MODE_PRIVATE);
        return settings.getString(KEY_LOYALTY_CODE, defeult);
    }

}
