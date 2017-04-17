package com.example.tigranhovhannisyan.mathtestapp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by tigran.hovhannisyan on 4/17/2017.
 */

public class ErrorAlertDialog extends DialogFragment {
    public static final String TAG = "ErrorAlertDialog";
    /// Key's for Bundle object
    private static final String IMAGE_RES_KEY = "image_res_key";
    private static final String TITLE_TEXT_KEY = "title_text_key";
    private static final String ERROR_MSG_TEXT_KEY = "error_massage_key";
    private static final String POS_BUTTON_TEXT_KEY = "pos_button_text_key";
    private static final String NEG_BUTTON_TEXT_KEY = "neg_button_text_key";

    private int imageRes;
    private CharSequence titleText;
    private CharSequence errorMsgText;
    private CharSequence posButtonText;
    private CharSequence negButtonText;
    private OnPositiveButtonClick onPositiveButtonClick;
    private OnNegativeButtonClick onNegativeButtonClick;

    private ImageView imageView;
    private TextView titleTextView;
    private TextView massageTextView;
    private Button posButton;
    private Button negButton;
    private LinearLayout posButtonLayout;
    private LinearLayout negButtonLayout;

    /// if some argument is not defined, the appropriate view will be hide

    public TextView getTitleTextView(){
        return titleTextView;
    }

    public TextView getMassageTextView(){
        return massageTextView;
    }

    public static <S extends CharSequence> ErrorAlertDialog getInstance(int imageRes, S titleText, S errorMassageText, S posButtonText, S negButtonText) {
        Bundle bundle = new Bundle();

        bundle.putInt(IMAGE_RES_KEY, imageRes);
        bundle.putCharSequence(TITLE_TEXT_KEY, titleText);
        bundle.putCharSequence(ERROR_MSG_TEXT_KEY, errorMassageText);
        bundle.putCharSequence(POS_BUTTON_TEXT_KEY, posButtonText);
        bundle.putCharSequence(NEG_BUTTON_TEXT_KEY, negButtonText);

        ErrorAlertDialog errorAlertDialog = new ErrorAlertDialog();
        errorAlertDialog.setArguments(bundle);

        return errorAlertDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View errorAlertDialog = inflater.inflate(R.layout.error_alert_dialog, null);

        imageView = (ImageView) errorAlertDialog.findViewById(R.id.error_alert_dialog_imageView);
        titleTextView = (TextView) errorAlertDialog.findViewById(R.id.error_alert_dialog_textView_title);
        massageTextView = (TextView) errorAlertDialog.findViewById(R.id.error_alert_dialog_textView_error_massage);
        posButton= (Button) errorAlertDialog.findViewById(R.id.error_alert_dialog_positive_button);
        negButton= (Button) errorAlertDialog.findViewById(R.id.error_alert_dialog_negative_button);
        posButtonLayout = (LinearLayout) errorAlertDialog.findViewById(R.id.error_alert_dialog_positive_button_layout);
        negButtonLayout = (LinearLayout) errorAlertDialog.findViewById(R.id.error_alert_dialog_negative_button_layout);


        return setUp(errorAlertDialog);
    }

    public void setPositiveButtonClickListener(OnPositiveButtonClick p) {
        this.onPositiveButtonClick = p;
    }

    public void setNegativeButtonClickListener(OnNegativeButtonClick n) {
        this.onNegativeButtonClick = n;
    }


    public interface OnPositiveButtonClick {
        void onPositiveButtonClick(String data);
    }

    public interface OnNegativeButtonClick {
        void onNegativeButtonClick(String data);
    }

    /*check the arguments inserted in the getInstance() method, and set up the dialog view
    *@return Dialog
    */
    private Dialog setUp(View view) {

        Bundle bundle = getArguments();

        this.imageRes = bundle.getInt(IMAGE_RES_KEY);
        this.titleText = bundle.getCharSequence(TITLE_TEXT_KEY);
        this.errorMsgText = bundle.getCharSequence(ERROR_MSG_TEXT_KEY);
        this.posButtonText = bundle.getCharSequence(POS_BUTTON_TEXT_KEY);
        this.negButtonText = bundle.getCharSequence(NEG_BUTTON_TEXT_KEY);

        if (imageRes == 0) {
            imageView.setVisibility(View.GONE);
        } else {
            imageView.setImageResource(imageRes);
        }

        if (titleText == null) {
            titleTextView.setVisibility(View.GONE);
        } else {
            titleTextView.setText(titleText);
        }
        if (errorMsgText == null) {
            massageTextView.setVisibility(View.GONE);
        } else {
            massageTextView.setText(errorMsgText);
        }

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setView(view);

        AlertDialog dialoog = alertDialog.create();

        if (posButtonText != null) {
            posButton.setText(posButtonText);
            posButton.setOnClickListener(v -> {
                DialogUtils.isShowDialog = false;
                if (onPositiveButtonClick != null) {
                    onPositiveButtonClick.onPositiveButtonClick("positive");
                }
                dialoog.cancel();
            });
        }else{
            posButtonLayout.setVisibility(View.GONE);
        }

        if (negButtonText != null) {
            negButton.setText(negButtonText);
            negButton.setOnClickListener(v -> {
                DialogUtils.isShowDialog = false;
                if (onNegativeButtonClick != null) {
                    onNegativeButtonClick.onNegativeButtonClick("negative");
                }
                dialoog.cancel();
            });
        }else {
            negButtonLayout.setVisibility(View.GONE);
        }



        return dialoog;
    }
}
