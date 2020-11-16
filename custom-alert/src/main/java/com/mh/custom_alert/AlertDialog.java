package com.mh.custom_alert;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.Objects;

public abstract class AlertDialog implements View.OnClickListener, DialogInterface.OnDismissListener {

    protected Activity act;

    protected View view;
    private Dialog dialog;

    private ImageView ivCircle;
    private TextView tvTitle;
    private TextView tvMessage;
    private ScrollView scrollView;
    private LinearLayout llView;

    protected TextView btnNegative;
    protected TextView btnNeutral;
    protected TextView btnPositive;
    protected View vH, vv1, vv2;

    protected boolean setMarginTop;
    protected boolean fullAlert = false;

    protected Btn backNeu;
    protected Btn backNeg;
    protected Btn backPos;

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
        tvTitle = view.findViewById(R.id.tvTitle);
        tvMessage = view.findViewById(R.id.tvMessage);
        scrollView = view.findViewById(R.id.scrollView);
        llView = view.findViewById(R.id.llView);
        btnNeutral = view.findViewById(R.id.btnNeutral);
        btnNegative = view.findViewById(R.id.btnNegative);
        btnPositive = view.findViewById(R.id.btnPositive);

        vH = view.findViewById(R.id.viewH);
        vv1 = view.findViewById(R.id.viewV1);
        vv2 = view.findViewById(R.id.viewV2);

        view.findViewById(R.id.ivClose).setOnClickListener(this);
        dialog.setOnDismissListener(this);

        backNeu = new Btn();
        backNeg = new Btn();
        backPos = new Btn();

        setBtn(backNeu, btnNeutral);
        setBtn(backNeg, btnNegative);
        setBtn(backPos, btnPositive);
    }

    private void setBtn(Btn b, TextView t) {
        b.btn = t;
        b.backColor = android.R.color.transparent;
        b.pressColor = R.color.colorClickButton;
        b.alpha = 95;
    }

    public void setCancelable(boolean cancelable) {
        dialog.setCancelable(cancelable);
    }

    public void setColor(int color) {
        //setColor(color, ivCircle.getBackground());
        setColor(color, R.drawable.circle_alert);
    }

    public void setType(int[] type) {
        setColor(type[0]);
        setIcon(type[1]);
        view.findViewById(R.id.relPrg).setVisibility(View.GONE);
        ivCircle.setVisibility(View.VISIBLE);
        setMarginTop = true;
    }

    public void setType(int type) {
        if (type == Type.PROGRESS) {
            setMarginTop = true;
            view.findViewById(R.id.relPrg).setVisibility(View.VISIBLE);
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
        tvTitle.setText(Html.fromHtml(title));
        if (title.isEmpty()) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setVisibility(View.VISIBLE);
        }
    }

    public void setMessage(String message) {
        tvMessage.setText(Html.fromHtml(message));
        if (message.isEmpty()) {
            tvMessage.setVisibility(View.GONE);
        } else {
            tvMessage.setVisibility(View.VISIBLE);
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
        setBtn(btnNeutral, text, null);
    }

    public void setNegativeText(String text) {
        setBtn(btnNegative, text, null);
    }

    public void setPositiveText(String text) {
        setBtn(btnPositive, text, null);
    }

    public void setNeutralText(String text, View.OnClickListener listener) {
        setBtn(btnNeutral, text, listener);
    }

    public void setNegativeText(String text, View.OnClickListener listener) {
        setBtn(btnNegative, text, listener);
    }

    public void setPositiveText(String text, View.OnClickListener listener) {
        setBtn(btnPositive, text, listener);
    }

    public void setNeutralTextColor(int color) {
        btnNeutral.setTextColor(ContextCompat.getColor(act, color));
    }

    public void setNegativeTextColor(int color) {
        //btnNegative.setTextColor(ContextCompat.getColor(act, R.color.colorSuccessCustomAlert));
        btnNegative.setTextColor(ContextCompat.getColor(act, color));
    }

    public void setPositiveTextColor(int color) {
        btnPositive.setTextColor(ContextCompat.getColor(act, color));
    }

    public void setNeutralBackColor(int color) {
        backNeu.backColor = color;
    }

    public void setNegativeBackColor(int color) {
        backNeg.backColor = color;
    }

    public void setPositiveBackColor(int color) {
        backPos.backColor = color;
    }

    public void setNeutralColorPress(int color, int alpha) {
        //btnNeutral.setBackground(utils.makeSelector(act.getResources().getColor(color), alpha));
        //backNeutral[0] = color;
        //backNeutral[1] = alpha;

        backNeu.btn = btnNeutral;
        backNeu.pressColor = color;
        backNeu.alpha = alpha;
    }

    public void setNegativeColorPress(int color, int alpha) {
        //btnNegative.setBackground(utils.makeSelector(act.getResources().getColor(color), alpha));
        //backNegative[0] = color;
        //backNegative[1] = alpha;

        backNeg.btn = btnNegative;
        backNeg.pressColor = color;
        backNeg.alpha = alpha;
    }

    public void setPositiveColorPress(int color, int alpha) {
        //btnPositive.setBackground(makeSelector(act.getResources().getColor(color), alpha));
        //backPositive[0] = color;
        //backPositive[1] = alpha;

        backPos.btn = btnPositive;
        backPos.pressColor = color;
        backPos.alpha = alpha;
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
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;

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
                /*TODO ((ImageView) custom_view_identity.findViewById(R.id.circleView)).setImageDrawable(null);
                ((ImageView) custom_view_identity.findViewById(R.id.close)).setImageDrawable(null);
                custom_view_identity.findViewById(R.id.buttonLeft).setBackground(null);
                custom_view_identity.findViewById(R.id.buttonRight).setBackground(null);*/
    }
}
