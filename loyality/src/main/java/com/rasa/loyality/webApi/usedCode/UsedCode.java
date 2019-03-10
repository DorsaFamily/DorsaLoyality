package com.rasa.loyality.webApi.usedCode;

import com.rasa.loyality.webApi.ErrorCode;
import com.rasa.loyality.webApi.WebService;
import com.rasa.loyality.webApi.usedCode.model.ResponseUsedCode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsedCode implements IUsedCode {

    private IUsedCode.iResult iResult;

    public UsedCode(IUsedCode.iResult iResult) {
        this.iResult = iResult;
    }

    public UsedCode() {
    }

    @Override
    public void usedCode(final String code) {
        Call<ResponseUsedCode> call = new WebService().getClient().create(IUsedCode.apiRequest.class)
                .usedCode(code);

        call.enqueue(new Callback<ResponseUsedCode>() {
            @Override
            public void onResponse(Call<ResponseUsedCode> call, Response<ResponseUsedCode> response) {
                if (response.code() == 200) {
                    if (response.body().getOk()) {
                        if (iResult != null) {
                            iResult.onSuccessUsedCode(code);
                        }
                    } else {
                        if (iResult != null) {
                            if(response.body().getMessages().size()>0) {
                                iResult.onFailedUsedCode(ErrorCode.errorCodeOther,response.body().getMessages().get(0).getDescription());
                            }else{
                                iResult.onFailedUsedCode(ErrorCode.errorCodeServer, "بروز اشکال در سرور مجددا تلاش نمایید");
                            }
                        }
                    }
                }else {
                    if (iResult != null) {
                        iResult.onFailedUsedCode(ErrorCode.errorCodeServer, "بروز اشکال در سرور مجددا تلاش نمایید");
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseUsedCode> call, Throwable t) {
                if (iResult != null) {
                    iResult.onFailedUsedCode(ErrorCode.errorCodeInternetConnection, "اشکال در اتصال به اینترنت");
                }
            }
        });
    }
}
