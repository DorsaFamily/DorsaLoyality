package com.rasa.loyality.utils.customView.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rasa.loyality.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DialogMessage extends Dialog {

    private TextView textMessage;
    private Button btnOk;
    private Button btnCancel;
    private Button btnExtra;

    private OnClick onClickOk;
    private OnClick onClickCancel;
    private OnClick onClickExtra;


    public DialogMessage(@NonNull Context context) {
        super(context);
        init();
    }


    public DialogMessage(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    protected DialogMessage(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init() {
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.loyalty_dialog_msg);
        textMessage = findViewById(R.id.textView24);
        btnOk = findViewById(R.id.button3);
        btnCancel = findViewById(R.id.button4);
        btnExtra = findViewById(R.id.button5);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
                if (onClickOk != null) {
                    onClickOk.onClicked();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
                if (onClickCancel != null) {
                    onClickCancel.onClicked();
                }
            }
        });
        btnExtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
                if (onClickExtra != null) {
                    onClickExtra.onClicked();
                }
            }
        });

    }

    public void setMessage(String message) {
        if (message != null) {
            textMessage.setText(message);

            if(message.contains("http")){
                textMessage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = "http://2rsa.ir/fandoghestan.html";
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        getContext().startActivity(i);
                    }
                });

            }

        }
    }

    public void setOnClickOk(String text, OnClick onClickOk) {
        this.onClickOk = onClickOk;
        btnOk.setText(text);
    }

    public void setOnClickCancel(String text, OnClick onClickCancel) {
        this.onClickCancel = onClickCancel;
        btnCancel.setText(text);
    }

    public void setOnClickExtra(String text, OnClick onClickExtra) {
        this.onClickExtra = onClickExtra;
        setShowExtraBtn(true);
        btnExtra.setText(text);
    }

    public void setShowOkButton(boolean show){
        if(show){
            btnOk.setVisibility(View.VISIBLE);
        }else{
            btnOk.setVisibility(View.GONE);
        }
    }

    public void setShowCancelButton(boolean show){
        if(show){
            btnCancel.setVisibility(View.VISIBLE);
        }else{
            btnCancel.setVisibility(View.GONE);
        }
    }

    public void setShowExtraBtn(boolean show){
        if(show){
            btnExtra.setVisibility(View.VISIBLE);
        }else{
            btnExtra.setVisibility(View.GONE);
        }
    }

    public interface OnClick {
        void onClicked();
    }


}
