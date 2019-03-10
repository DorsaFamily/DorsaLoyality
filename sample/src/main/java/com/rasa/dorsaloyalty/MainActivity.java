package com.rasa.dorsaloyalty;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.rasa.loyality.Loyalty;

public class MainActivity extends AppCompatActivity {

    private final String TAG="LOYALTY_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Loyalty.startLoyalty(this,123);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==123){
            if (resultCode== Activity.RESULT_OK){
                Log.d(TAG, "onActivityResult: OK");
            }else{
                Log.d(TAG, "onActivityResult: FAILED");
            }
        }
    }
}
