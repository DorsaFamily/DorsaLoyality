package com.rasa.loyality.webApi.usedCode;

import com.rasa.loyality.webApi.usedCode.model.ResponseUsedCode;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IUsedCode {
    void usedCode(String code);

    interface iResult {
        void onSuccessUsedCode(String code);
        void onFailedUsedCode(int errorId, String errorMessage);
    }

    interface apiRequest {
        @POST("Discount/UsedCode")
        Call<ResponseUsedCode> usedCode(@Query("code") String code);
    }

}
