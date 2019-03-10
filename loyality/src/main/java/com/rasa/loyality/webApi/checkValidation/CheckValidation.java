package com.rasa.loyality.webApi.checkValidation;

import com.rasa.loyality.webApi.ErrorCode;
import com.rasa.loyality.webApi.WebService;
import com.rasa.loyality.webApi.checkValidation.model.ResponseCheckValidation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckValidation implements ICheckValidation {

    private ICheckValidation.iResult iResult;


    public CheckValidation(ICheckValidation.iResult iResult) {
        this.iResult = iResult;
    }

    public CheckValidation() {
    }

    @Override
    public void checkValidation(String code) {
        Call<ResponseCheckValidation> call = new WebService().getClient().create(ICheckValidation.apiRequest.class)
                .checkValidation(code);

        call.enqueue(new Callback<ResponseCheckValidation>() {
            @Override
            public void onResponse(Call<ResponseCheckValidation> call, Response<ResponseCheckValidation> response) {
                if (response.code() == 200) {
                    if (response.body().getOk()) {
                        if (iResult != null) {
                            iResult.onSuccessCheckValidation();
                        }
                    } else {
                        if (iResult != null) {
                            if(response.body().getMessages().size()>0) {
                                iResult.onFailedCheckValidation(ErrorCode.errorCodeOther,response.body().getMessages().get(0).getDescription());
                            }else{
                                iResult.onFailedCheckValidation(ErrorCode.errorCodeServer, "بروز اشکال در سرور مجددا تلاش نمایید");
                            }
                        }
                    }
                }else {
                    if (iResult != null) {
                        iResult.onFailedCheckValidation(ErrorCode.errorCodeServer, "بروز اشکال در سرور مجددا تلاش نمایید");
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseCheckValidation> call, Throwable t) {
                if (iResult != null) {
                    iResult.onFailedCheckValidation(ErrorCode.errorCodeInternetConnection, "اشکال در اتصال به اینترنت");
                }
            }
        });
    }
}
