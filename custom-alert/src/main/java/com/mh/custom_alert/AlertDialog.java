package com.mh.custom_alert;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.TypedValue;
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

import pl.droidsonroids.gif.GifImageView;

//TODO SetEnter animation and exit animation
//TODO SetFonts

public class AlertDialog implements View.OnClickListener, DialogInterface.OnDismissListener {

    private Activity act;

    private View view;
    private Dialog dialog;

    private ImageView ivCircle;
    private ProgressBar prgCircle;
    private TextView tvT, tvM;
    private ScrollView scrollView;
    private LinearLayout llView;

    private TextView btnX;
    private TextView btnN;
    private TextView btnP;
    private View vH, vv1, vv2;

    private boolean setMarginTop;
    private boolean fullAlert = false;

    private Btn backX;
    private Btn backN;
    private Btn backP;

    private static class Btn {
        protected int backColor;
        protected int pressColor;
        protected int alpha;
    }

    @SuppressLint("InflateParams")
    public AlertDialog(Activity act, int theme) {

        this.act = act;
        dialog = new Dialog(act);
        switch (theme) {
            case Theme.SYSTEM:
                int nightModeFlags = act.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

                if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
                    act.setTheme(R.style.CustomAlertDarkTheme);
                } else {
                    act.setTheme(R.style.CustomAlertTheme);
                }
                break;

            case Theme.LIGHT:
                act.setTheme(R.style.CustomAlertTheme);
                break;

            case Theme.DARK:
                act.setTheme(R.style.CustomAlertDarkTheme);
                break;
        }

        LayoutInflater inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = Objects.requireNonNull(inflater).inflate(R.layout.customalert_lib, null);

        initBindings();
        initListeners();
    }

    private void initBindings() {

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

        backX = new Btn();
        backN = new Btn();
        backP = new Btn();

        setBtn(backX);
        setBtn(backN);
        setBtn(backP);
    }

    private void initListeners() {
        view.findViewById(R.id.ivClose).setOnClickListener(this);
        dialog.setOnDismissListener(this);
    }

    private void setBtn(Btn b) {
        b.backColor = android.R.color.transparent;
        b.pressColor = R.color.colorClickButton;
        b.alpha = 65;
    }

    protected void setCancelable(boolean c) {
        dialog.setCancelable(c);
    }

    protected void setColor(int c) {
        Drawable drawable = ivCircle.getBackground();
        if (drawable instanceof ShapeDrawable) {
            ((ShapeDrawable) drawable).getPaint().setColor(ContextCompat.getColor(act, c));
        } else if (drawable instanceof GradientDrawable) {
            ((GradientDrawable) drawable).setColor(ContextCompat.getColor(act, c));
        } else if (drawable instanceof ColorDrawable) {
            ((ColorDrawable) drawable).setColor(ContextCompat.getColor(act, c));
        }
    }

    protected void setColorTitle(int c) {
        tvT.setTextColor(ContextCompat.getColor(act, c));
    }

    protected void setColorMessage(int c) {
        tvM.setTextColor(ContextCompat.getColor(act, c));
    }

    protected void setColorPrg(int c) {
        prgCircle.setIndeterminateTintList(ColorStateList.valueOf(ContextCompat.getColor(act, c)));
    }

    protected void setType(int[] t) {
        setColor(t[0]);
        setIcon(t[1]);
        prgCircle.setVisibility(View.GONE);
        ivCircle.setVisibility(View.VISIBLE);
        setMarginTop = true;
    }

    protected void setType(int t) {
        if (t == Type.PROGRESS) {
            setMarginTop = true;
            setIcon(0);
            prgCircle.setVisibility(View.VISIBLE);
        }
    }

    protected void setIcon(Integer i) {
        if (i == null) {
            ivCircle.setVisibility(View.GONE);
        } else {
            ivCircle.setImageResource(i);
        }
        setMarginTop = i != null;
    }

    protected void setTitle(String t) {
        setTxt(tvT, t);
    }

    protected void setMessage(String m) {
        setTxt(tvM, m);
    }

    protected void setTxt(TextView t, String s) {
        t.setText(Html.fromHtml(s));
        if (s.isEmpty()) {
            t.setVisibility(View.GONE);
        } else {
            t.setVisibility(View.VISIBLE);
        }
    }

    protected void setView(View v) {
        if (v == null) {
            llView.removeAllViews();
            llView.setVisibility(View.GONE);
        } else {
            llView.addView(v);
            llView.setVisibility(View.VISIBLE);
        }

        RelativeLayout.LayoutParams p1 = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        p1.addRule(RelativeLayout.BELOW, R.id.tvMessage);
        scrollView.setLayoutParams(p1);

        setHeight();
    }

    @SuppressLint("InflateParams")
    protected void setGif(Integer g) {
        if (g == null) {
            llView.removeAllViews();
            llView.setVisibility(View.GONE);
        } else {
            View v = act.getLayoutInflater().inflate(R.layout.customalert_gif, null);
            ((GifImageView) v.findViewById(R.id.ivGif)).setImageResource(g);

            LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 350);
            v.setLayoutParams(p2);

            llView.addView(v);
            llView.setVisibility(View.VISIBLE);
        }
    }

    protected void setNeutralText(String t) {
        setBtn(btnX, t, this);
    }

    protected void setNegativeText(String t) {
        setBtn(btnN, t, this);
    }

    protected void setPositiveText(String t) {
        setBtn(btnP, t, this);
    }

    protected void setNeutralText(String t, View.OnClickListener l) {
        setBtn(btnX, t, l);
    }

    protected void setNegativeText(String t, View.OnClickListener l) {
        setBtn(btnN, t, l);
    }

    protected void setPositiveText(String t, View.OnClickListener l) {
        setBtn(btnP, t, l);
    }

    protected void setNeutralTextColor(int c) {
        btnX.setTextColor(ContextCompat.getColor(act, c));
    }

    protected void setNegativeTextColor(int c) {
        btnN.setTextColor(ContextCompat.getColor(act, c));
    }

    protected void setPositiveTextColor(int c) {
        btnP.setTextColor(ContextCompat.getColor(act, c));
    }

    protected void setNeutralBackColor(int c) {
        backX.backColor = c;
    }

    protected void setNegativeBackColor(int c) {
        backN.backColor = c;
    }

    protected void setPositiveBackColor(int c) {
        backP.backColor = c;
    }

    protected void setNeutralColorPress(int c, int a) {
        backX.pressColor = c;
        backX.alpha = a;
    }

    protected void setNegativeColorPress(int c, int a) {
        backN.pressColor = c;
        backN.alpha = a;
    }

    protected void setPositiveColorPress(int c, int a) {
        backP.pressColor = c;
        backP.alpha = a;
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

    protected void setBtn(TextView txt, String text, View.OnClickListener l) {
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

    protected void setFullAlert(boolean fa) {
        fullAlert = fa;
    }

    protected void hideClose(boolean h) {
        if (h) {
            view.findViewById(R.id.ivClose).setVisibility(View.GONE);
        } else {
            view.findViewById(R.id.ivClose).setVisibility(View.VISIBLE);
        }
    }

    protected void show() {

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.CustomAlertDialogTheme;

        checkFullAlert();
        checkButtons();

        dialog.show();
    }

    protected void dismiss() {
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

    // =============================================================================================
    private void checkFullAlert() {
        if (fullAlert) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, 0);
            view.findViewById(R.id.relAlert).setLayoutParams(params);
            if (setMarginTop) {
                int marginInDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, act.getResources().getDisplayMetrics());
                RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0);
                params2.setMargins(0, marginInDp, 0, 0);
                view.findViewById(R.id.view).setLayoutParams(params2);
            }
        }
    }

    private void checkButtons() {
        if (!isX() && !isN() && !isP()) {           // 0 0 0
            vH.setVisibility(View.GONE);
            vv1.setVisibility(View.GONE);
            vv2.setVisibility(View.GONE);
        } else if (!isX() && !isN() && isP()) {     // 0 0 1
            vH.setVisibility(View.VISIBLE);
            vv1.setVisibility(View.GONE);
            vv2.setVisibility(View.GONE);
            btnP.setBackground(makeSelector(backP, 1, 1));
        } else if (!isX() && isN() && !isP()) {     // 0 1 0
            vH.setVisibility(View.VISIBLE);
            vv1.setVisibility(View.GONE);
            vv2.setVisibility(View.GONE);
            btnN.setBackground(makeSelector(backN, 1, 1));
        } else if (!isX() && isN() && isP()) {      // 0 1 1
            vH.setVisibility(View.VISIBLE);
            vv1.setVisibility(View.GONE);
            vv2.setVisibility(View.VISIBLE);
            btnN.setBackground(makeSelector(backN, 1, 0));
            btnP.setBackground(makeSelector(backP, 0, 1));
        } else if (isX() && !isN() && !isP()) {     // 1 0 0
            vH.setVisibility(View.VISIBLE);
            vv1.setVisibility(View.GONE);
            vv2.setVisibility(View.GONE);
            btnX.setBackground(makeSelector(backX, 1, 1));
        } else if (isX() && !isN() && isP()) {      // 1 0 1
            vH.setVisibility(View.VISIBLE);
            vv1.setVisibility(View.VISIBLE);
            vv2.setVisibility(View.GONE);
            btnX.setBackground(makeSelector(backX, 1, 0));
            btnP.setBackground(makeSelector(backP, 0, 1));
        } else if (isX() && isN() && !isP()) {      // 1 1 0
            vH.setVisibility(View.VISIBLE);
            vv1.setVisibility(View.VISIBLE);
            vv2.setVisibility(View.GONE);
            btnX.setBackground(makeSelector(backX, 1, 0));
            btnN.setBackground(makeSelector(backN, 0, 1));
        } else if (isX() && isN() && isP()) {       // 1 1 1
            vH.setVisibility(View.VISIBLE);
            vv1.setVisibility(View.VISIBLE);
            vv2.setVisibility(View.VISIBLE);
            btnX.setBackground(makeSelector(backX, 1, 0));
            btnN.setBackground(makeSelector(backN, 0, 0));
            btnP.setBackground(makeSelector(backP, 0, 1));
        }
    }

    private StateListDrawable makeSelector(Btn btn, int l, int r) {
        StateListDrawable res = new StateListDrawable();
        res.setExitFadeDuration(800);
        res.setAlpha(btn.alpha);

        res.addState(new int[]{android.R.attr.state_pressed}, createShape(true, btn.pressColor, l, r));
        res.addState(new int[]{}, createShape(false, btn.backColor, l, r));

        return res;
    }

    private GradientDrawable createShape(boolean stroke, int c, int l, int r) {

        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadii(new float[]{0, 0, 0, 0, 20 * r, 20 * r, 20 * l, 20 * l});
        shape.setColor(ContextCompat.getColor(act, c));
        if (stroke)
            shape.setStroke(6, (ContextCompat.getColor(act, android.R.color.white)));

        return shape;
    }

    private boolean isX() {
        return btnX.getVisibility() == View.VISIBLE;
    }

    private boolean isN() {
        return btnN.getVisibility() == View.VISIBLE;
    }

    private boolean isP() {
        return btnP.getVisibility() == View.VISIBLE;
    }
}
