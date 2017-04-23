package com.example.tigranhovhannisyan.mathtestapp;

import android.app.Activity;
import android.app.ProgressDialog;

import android.support.v4.app.FragmentActivity;



/**
 * Created by andranik on 2/17/16.
 */
public class DialogUtils {
    // isShowDialog specify is there any dialog which attached in the screen
    // (double dialog issue)
    public static boolean isShowDialog = false;

    public static ProgressDialog showProgressDialog(Activity activity, String message, boolean cancalable) {
        ProgressDialog progress = new ProgressDialog(activity);
        progress.setMessage(message);
        progress.setCancelable(cancalable);
        progress.setCanceledOnTouchOutside(false);
        progress.show();

        return progress;
    }

    public static ErrorAlertDialog showAlertDialog(FragmentActivity activity, int msgId) {
        return showAlertDialog(activity, activity.getString(msgId));
    }

    public static <S extends CharSequence> ErrorAlertDialog showAlertDialog(FragmentActivity activity, S msg) {
        return showAlertDialog(activity,
                0,
                null,
                msg,
                activity.getString(R.string.dialog_ok),
                null,
                null,
                null);
    }

    public static <S extends CharSequence> ErrorAlertDialog showAlertDialog(FragmentActivity activity, S msg, int posBtnTextId, int negBtnTextId, ErrorAlertDialog.OnPositiveButtonClick posBtnClickListener) {
        return showAlertDialog(activity,
                0,
                null,
                msg,
                activity.getString(posBtnTextId),
                activity.getString(negBtnTextId),
                posBtnClickListener,
                null);
    }

    public static ErrorAlertDialog showAlertDialog(FragmentActivity activity, String message, ErrorAlertDialog.OnPositiveButtonClick posBtnClickListener) {

        return showAlertDialog(activity,
                0,
                null,
                message,
                activity.getString(R.string.dialog_ok),
                null,
                posBtnClickListener,
                null);
    }

    public static ErrorAlertDialog showAlertDialog(FragmentActivity activity, int msgId, ErrorAlertDialog.OnPositiveButtonClick posBtnClickListener) {

        return showAlertDialog(activity,
                0,
                null,
                activity.getString(msgId),
                activity.getString(R.string.dialog_ok),
                null,
                posBtnClickListener,
                null);
    }

    public static ErrorAlertDialog showAlertDialog(FragmentActivity activity, int msgId, int resId, ErrorAlertDialog.OnPositiveButtonClick posBtnClickListener) {

        return showAlertDialog(activity,
                resId,
                null,
                activity.getString(msgId),
                activity.getString(R.string.dialog_ok),
                null,
                posBtnClickListener,
                null);
    }

    public static <S extends CharSequence> ErrorAlertDialog showAlertDialog(FragmentActivity activity, S message, int resId, ErrorAlertDialog.OnPositiveButtonClick posBtnClickListener) {

        return showAlertDialog(activity,
                resId,
                null,
                message,
                activity.getString(R.string.dialog_ok),
                null,
                posBtnClickListener,
                null);
    }

    public static ErrorAlertDialog showAlertDialog(FragmentActivity activity, int msgId, ErrorAlertDialog.OnPositiveButtonClick posBtnClickListener, ErrorAlertDialog.OnNegativeButtonClick negBtnClickListener) {

        return showAlertDialog(activity,
                0,
                null,
                activity.getString(msgId),
                activity.getString(R.string.dialog_ok),
                activity.getString(R.string.dialog_cancel),
                posBtnClickListener,
                negBtnClickListener);
    }

    public static ErrorAlertDialog showAlertDialog(FragmentActivity activity, int resId, String message, ErrorAlertDialog.OnPositiveButtonClick posBtnClickListener, ErrorAlertDialog.OnNegativeButtonClick negBtnClickListener) {

        return showAlertDialog(activity,
                resId,
                null,
                message,
                activity.getString(R.string.dialog_ok),
                activity.getString(R.string.dialog_cancel),
                posBtnClickListener,
                negBtnClickListener);
    }

    public static <S extends CharSequence> ErrorAlertDialog showAlertDialog(FragmentActivity activity, int resId, S title, S massage, S posBtnText, S negBtnText, ErrorAlertDialog.OnPositiveButtonClick posBtnClickListener, ErrorAlertDialog.OnNegativeButtonClick negBtnClickListener) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//
//        builder.setMessage(massage);
//        builder.setCancelable(false);
//        if(title != null){ builder.setTitle(title); }
//        if (posBtnText != null){ builder.setPositiveButton(posBtnText, posBtnClickListener); }
//        if (negBtnText != null){ builder.setNegativeButton(negBtnText, negBtnClickListener); }
//
//        AlertDialog alert = builder.create();
//        alert.show();
//


        ErrorAlertDialog alert = null;
        if (!isShowDialog) {
            isShowDialog = true;
            alert = ErrorAlertDialog.getInstance(resId, title, massage, posBtnText, negBtnText);
            alert.setCancelable(false);
            alert.setPositiveButtonClickListener(posBtnClickListener);
            alert.show(activity.getSupportFragmentManager(), ErrorAlertDialog.TAG);
            if(negBtnClickListener != null){
                alert.setNegativeButtonClickListener(negBtnClickListener);
            }
        }
        return alert;
    }

}
