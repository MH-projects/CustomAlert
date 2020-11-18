package com.mh.custom_alert;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.Objects;

//TODO SetEnter animation and exit animation
//TODO SetFonts

public abstract class AlertDialog implements View.OnClickListener, DialogInterface.OnDismissListener {

    protected Activity act;

    protected View view;
    private Dialog dialog;

    protected ImageView ivCircle;
    private ProgressBar prgCircle;
    private TextView tvT, tvM;
    private ScrollView scrollView;
    private LinearLayout llView;

    protected TextView btnX;
    protected TextView btnN;
    protected TextView btnP;
    protected View vH, vv1, vv2;

    protected boolean setMarginTop;
    protected boolean fullAlert = false;

    protected Btn backX;
    protected Btn backN;
    protected Btn backP;

    protected static class Btn {
        protected TextView btn;
        protected int backColor;
        protected int pressColor;
        protected int alpha;
    }

    protected abstract Drawable setColor(int i, int d);

    protected abstract void checkFullAlert();

    protected abstract void checkButtons();

    @SuppressLint("InflateParams")
    public AlertDialog(Activity act) {

        this.act = act;
        dialog = new Dialog(act);

        LayoutInflater inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = Objects.requireNonNull(inflater).inflate(R.layout.custom_alert, null);

        ivCircle = view.findViewById(R.id.circleView);
        prgCircle = view.findViewById(R.id.prgCircle);

        tvT = view.findViewById(R.id.tvTitle);
        tvM = view.findViewById(R.id.tvMessage);
        scrollView = view.findViewById(R.id.scrollView);
        llView = view.findViewById(R.id.llView);
        btnX = view.findViewById(R.id.btnNeutral);
        btnN = view.findViewById(R.id.btnNegative);
        btnP = view.findViewById(R.id.btnPositive);

        vH = view.findViewById(R.id.viewH);
        vv1 = view.findViewById(R.id.viewV1);
        vv2 = view.findViewById(R.id.viewV2);

        view.findViewById(R.id.ivClose).setOnClickListener(this);
        dialog.setOnDismissListener(this);

        backX = new Btn();
        backN = new Btn();
        backP = new Btn();

        setBtn(backX, btnX);
        setBtn(backN, btnN);
        setBtn(backP, btnP);
    }

    private void setBtn(Btn b, TextView t) {
        b.btn = t;
        b.backColor = android.R.color.transparent;
        b.pressColor = R.color.colorClickButton;
        b.alpha = 65;
    }

    public void setCancelable(boolean cancelable) {
        dialog.setCancelable(cancelable);
    }

    public void setColor(int color) {
        Drawable drawable = ivCircle.getBackground();
        if (drawable instanceof ShapeDrawable) {
            ((ShapeDrawable) drawable).getPaint().setColor(ContextCompat.getColor(act, color));
        } else if (drawable instanceof GradientDrawable) {
            ((GradientDrawable) drawable).setColor(ContextCompat.getColor(act, color));
        } else if (drawable instanceof ColorDrawable) {
            ((ColorDrawable) drawable).setColor(ContextCompat.getColor(act, color));
        }
    }

    public void setColorTitle(int color) {
        tvT.setTextColor(ContextCompat.getColor(act, color));
    }

    public void setColorMessage(int color) {
        tvM.setTextColor(ContextCompat.getColor(act, color));
    }

    public void setColorPrg(int color) {
        prgCircle.setIndeterminateTintList(ColorStateList.valueOf(ContextCompat.getColor(act, color)));
    }

    public void setType(int[] type) {
        setColor(type[0]);
        setIcon(type[1]);
        prgCircle.setVisibility(View.GONE);
        ivCircle.setVisibility(View.VISIBLE);
        setMarginTop = true;
    }

    public void setType(int type) {
        if (type == Type.PROGRESS) {
            setMarginTop = true;
            setIcon(0);
            prgCircle.setVisibility(View.VISIBLE);
        }
    }

    public void setIcon(Integer icon) {
        if (icon == null) {
            ivCircle.setVisibility(View.GONE);
        } else {
            ivCircle.setImageResource(icon);
        }
        setMarginTop = icon != null;
    }

    public void setTitle(String title) {
        setTxt(tvT, title);
    }

    public void setMessage(String message) {
        setTxt(tvM, message);
    }

    private void setTxt(TextView t, String s) {
        t.setText(Html.fromHtml(s));
        if (s.isEmpty()) {
            t.setVisibility(View.GONE);
        } else {
            t.setVisibility(View.VISIBLE);
        }
    }

    public void setView(View view) {
        if (view == null) {
            llView.removeAllViews();
            llView.setVisibility(View.GONE);
        } else {
            llView.addView(view);
            llView.setVisibility(View.VISIBLE);
        }

        RelativeLayout.LayoutParams p1 = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        p1.addRule(RelativeLayout.BELOW, R.id.tvMessage);
        scrollView.setLayoutParams(p1);

        setHeight();
    }

    public void setNeutralText(String text) {
        setBtn(btnX, text, this);
    }

    public void setNegativeText(String text) {
        setBtn(btnN, text, this);
    }

    public void setPositiveText(String text) {
        setBtn(btnP, text, this);
    }

    public void setNeutralText(String text, View.OnClickListener listener) {
        setBtn(btnX, text, listener);
    }

    public void setNegativeText(String text, View.OnClickListener listener) {
        setBtn(btnN, text, listener);
    }

    public void setPositiveText(String text, View.OnClickListener listener) {
        setBtn(btnP, text, listener);
    }

    public void setNeutralTextColor(int color) {
        btnX.setTextColor(ContextCompat.getColor(act, color));
    }

    public void setNegativeTextColor(int color) {
        btnN.setTextColor(ContextCompat.getColor(act, color));
    }

    public void setPositiveTextColor(int color) {
        btnP.setTextColor(ContextCompat.getColor(act, color));
    }

    public void setNeutralBackColor(int color) {
        backX.backColor = color;
    }

    public void setNegativeBackColor(int color) {
        backN.backColor = color;
    }

    public void setPositiveBackColor(int color) {
        backP.backColor = color;
    }

    public void setNeutralColorPress(int color, int alpha) {
        backX.btn = btnX;
        backX.pressColor = color;
        backX.alpha = alpha;
    }

    public void setNegativeColorPress(int color, int alpha) {
        backN.btn = btnN;
        backN.pressColor = color;
        backN.alpha = alpha;
    }

    public void setPositiveColorPress(int color, int alpha) {
        backP.btn = btnP;
        backP.pressColor = color;
        backP.alpha = alpha;
    }

    private void setHeight() {

        final ViewGroup.LayoutParams p2 = scrollView.getLayoutParams();
        ViewTreeObserver vto = scrollView.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                scrollView.getViewTreeObserver().removeOnPreDrawListener(this);
                int finalHeight = scrollView.getMeasuredHeight();

                DisplayMetrics metrics = new DisplayMetrics();
                act.getWindowManager().getDefaultDisplay().getMetrics(metrics);
                int height = metrics.heightPixels;

                if ((finalHeight + 250) > (height - 100)) {
                    p2.height = height - 450;
                    scrollView.setLayoutParams(p2);
                }
                return true;
            }
        });
    }

    private void setBtn(TextView txt, String text, View.OnClickListener l) {
        if (text == null) {
            txt.setText("");
            txt.setVisibility(View.GONE);
        } else {
            txt.setText(text);
            txt.setVisibility(View.VISIBLE);
            txt.setOnClickListener(l);
        }
        checkButtons();
    }

    public void setFullAlert(boolean fullAlert) {
        this.fullAlert = fullAlert;
    }

    public void hideClose(boolean hide) {
        if (hide) {
            view.findViewById(R.id.ivClose).setVisibility(View.GONE);
        } else {
            view.findViewById(R.id.ivClose).setVisibility(View.VISIBLE);
        }
    }

    public void show() {

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.CustomAlertDialogTheme;

        checkFullAlert();
        checkButtons();

        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivClose) {
            dismiss();
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        setColor(R.color.colorAccentCustomAlert);
        ivCircle.setImageDrawable(null);
        ((ImageView) view.findViewById(R.id.ivClose)).setImageDrawable(null);
        btnX.setBackground(null);
        btnN.setBackground(null);
        btnP.setBackground(null);
        llView.removeAllViews();
    }
}
