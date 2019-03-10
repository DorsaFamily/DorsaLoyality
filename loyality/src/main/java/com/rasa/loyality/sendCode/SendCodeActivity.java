package com.rasa.loyality.sendCode;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

import com.rasa.loyality.R;
import com.rasa.loyality.utils.SpHelper;
import com.rasa.loyality.utils.customView.dialog.DialogMessage;
import com.rasa.loyality.webApi.ErrorCode;
import com.rasa.loyality.webApi.checkValidation.CheckValidation;
import com.rasa.loyality.webApi.checkValidation.ICheckValidation;

import androidx.appcompat.app.AppCompatActivity;

public class SendCodeActivity extends AppCompatActivity implements SendCodeFragment.onSendCodeResult {

    private static final String TAG_FRG_SEND_CODE = "TAG_FRG_SEND_CODE";
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loyalty_activity_send_code);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("در حال بررسی وضعیت...");
        pDialog.setCancelable(false);

        SpHelper spHelper = new SpHelper(this);


        if (spHelper.getLoyaltyCode("").isEmpty()) {//user has no code yet
            requestNewCode();
        } else {
            checkStatus();
        }
    }


    private void checkStatus() {
        final SpHelper spHelper = new SpHelper(this);
        pDialog.show();
        new CheckValidation(new ICheckValidation.iResult() {
            @Override
            public void onSuccessCheckValidation() {
                pDialog.cancel();
                onSuccess();
            }

            @Override
            public void onFailedCheckValidation(int errorId, String errorMessage) {
                final DialogMessage dialogMessage = new DialogMessage(SendCodeActivity.this);
                dialogMessage.setMessage(errorMessage);
                dialogMessage.setOnClickCancel("خروج", new DialogMessage.OnClick() {
                    @Override
                    public void onClicked() {
                        onCancel();
                    }
                });

                if (errorId == ErrorCode.errorCodeInternetConnection) {
                    dialogMessage.setOnClickOk("تلاش مجدد", new DialogMessage.OnClick() {
                        @Override
                        public void onClicked() {
                            checkStatus();
                        }
                    });
                } else if (errorId == ErrorCode.errorCodeServer) {
                    dialogMessage.setOnClickOk("تلاش مجدد", new DialogMessage.OnClick() {
                        @Override
                        public void onClicked() {
                            checkStatus();
                        }
                    });
                } else {
                    dialogMessage.setOnClickOk("کد جدید", new DialogMessage.OnClick() {
                        @Override
                        public void onClicked() {
                            spHelper.setLoyaltyCode("");
                            requestNewCode();
                        }
                    });
                }
                pDialog.cancel();
                dialogMessage.setCancelable(false);
                dialogMessage.show();
            }
        }).checkValidation(spHelper.getLoyaltyCode(""));
    }


    private void requestNewCode() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_main, new SendCodeFragment(), TAG_FRG_SEND_CODE)
                .commitAllowingStateLoss();
    }


    @Override
    public void onSuccess() {
        setResult(Activity.RESULT_OK);
        finish();
    }

    @Override
    public void onCancel() {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }
}
