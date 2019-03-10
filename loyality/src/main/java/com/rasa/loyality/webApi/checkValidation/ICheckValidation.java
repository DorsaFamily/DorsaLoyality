package com.rasa.loyality.webApi.checkValidation;

import com.rasa.loyality.webApi.checkValidation.model.ResponseCheckValidation;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ICheckValidation {
    void checkValidation(String code);

    interface iResult {
        void onSuccessCheckValidation();
        void onFailedCheckValidation(int errorId, String errorMessage);
    }

    interface apiRequest {
        @POST("Discount/ChackValidation")
        Call<ResponseCheckValidation> checkValidation(@Query("code") String code);
    }
}
