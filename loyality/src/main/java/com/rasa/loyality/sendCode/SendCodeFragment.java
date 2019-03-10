package com.rasa.loyality.sendCode;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rasa.loyality.R;
import com.rasa.loyality.utils.SpHelper;
import com.rasa.loyality.webApi.registerInformation.RegisterInfo;
import com.rasa.loyality.webApi.usedCode.IUsedCode;
import com.rasa.loyality.webApi.usedCode.UsedCode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SendCodeFragment extends Fragment {

    private View pView;
    private EditText textCode;
    private TextView textError;
    private Button btnSend;
    private Button btnCancel;

    private onSendCodeResult mListener;
    private ProgressDialog pDialog;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        pView = inflater.inflate(R.layout.loyalty_fragment_send_code, container, false);

        textCode = pView.findViewById(R.id.loyalty_text_code);
        textError = pView.findViewById(R.id.loyalty_text_error);

        btnSend = pView.findViewById(R.id.loyalty_btn_confirm);
        btnCancel = pView.findViewById(R.id.loyalty_btn_cancel);

        pDialog=new ProgressDialog(getContext());
        pDialog.setMessage("در حال ارسال کد ...");
        pDialog.setCancelable(false);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onCancel();
                }
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCode();
            }
        });




        return pView;
    }

    private void sendCode(){
        if(textCode.getText().toString().isEmpty()){
            textError.setText("کد تخفیف را وارد نمایید");
            textError.setVisibility(View.VISIBLE);
        }else{
            textError.setVisibility(View.GONE);
            pDialog.show();
            new UsedCode(new IUsedCode.iResult() {
                @Override
                public void onSuccessUsedCode(String code) {
                    pDialog.cancel();
                    SpHelper spHelper=new SpHelper(getContext());
                    spHelper.setLoyaltyCode(code);
                    new RegisterInfo(getContext()).install();
                    if (mListener != null) {
                        mListener.onSuccess();
                    }
                }

                @Override
                public void onFailedUsedCode(int errorId, String errorMessage) {
                    pDialog.cancel();
                        textError.setText(errorMessage);
                        textError.setVisibility(View.VISIBLE);
                }
            }).usedCode(textCode.getText().toString());
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onSendCodeResult){
            mListener=(onSendCodeResult) context;
        }
    }

    public interface onSendCodeResult{
        void onSuccess();
        void onCancel();
    }
}
