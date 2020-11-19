package com.mh.custom_alert;

import android.app.Activity;
import android.view.View;

public class CustomAlert extends AlertDialog {

    public CustomAlert(Activity activity) {
        super(activity);
    }

    public void setCancelable(boolean cancelable) {
        super.setCancelable(cancelable);
    }

    public void setColor(int color) {
        super.setColor(color);
    }

    public void setColorTitle(int color) {
        super.setColorTitle(color);
    }

    public void setColorMessage(int color) {
        super.setColorMessage(color);
    }

    public void setColorPrg(int color) {
        super.setColorPrg(color);
    }

    public void setType(int[] type) {
        super.setType(type);
    }

    public void setType(int type) {
        super.setType(type);
    }

    public void setIcon(Integer icon) {
        super.setIcon(icon);
    }

    public void setTitle(String title) {
        super.setTitle(title);
    }

    public void setMessage(String message) {
        super.setMessage(message);
    }

    public void setView(View view) {
        super.setView(view);
    }

    public void setGif(Integer gif) {
        super.setGif(gif);
    }

    public void setNeutralText(String text) {
        super.setNeutralText(text);
    }

    public void setNegativeText(String text) {
        super.setNegativeText(text);
    }

    public void setPositiveText(String text) {
        super.setPositiveText(text);
    }

    public void setNeutralText(String text, View.OnClickListener listener) {
        super.setNeutralText(text, listener);
    }

    public void setNegativeText(String text, View.OnClickListener listener) {
        super.setNegativeText(text, listener);
    }

    public void setPositiveText(String text, View.OnClickListener listener) {
        super.setPositiveText(text, listener);
    }

    public void setNeutralTextColor(int color) {
        super.setNeutralTextColor(color);
    }

    public void setNegativeTextColor(int color) {
        super.setNegativeTextColor(color);
    }

    public void setPositiveTextColor(int color) {
        super.setPositiveTextColor(color);
    }

    public void setNeutralBackColor(int color) {
        super.setNeutralBackColor(color);
    }

    public void setNegativeBackColor(int color) {
        super.setNegativeBackColor(color);
    }

    public void setPositiveBackColor(int color) {
        super.setPositiveBackColor(color);
    }

    public void setNeutralColorPress(int color, int alpha) {
        super.setNeutralColorPress(color, alpha);
    }

    public void setNegativeColorPress(int color, int alpha) {
        super.setNegativeColorPress(color, alpha);
    }

    public void setPositiveColorPress(int color, int alpha) {
        super.setPositiveColorPress(color, alpha);
    }

    public void setFullAlert(boolean fullAlert) {
        super.setFullAlert(fullAlert);
    }

    public void hideClose(boolean hide) {
        super.hideClose(hide);
    }

    public void show() {
        super.show();
    }

    public void dismiss() {
        super.dismiss();
    }
}
