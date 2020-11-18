package com.mh.custom_alert;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.core.content.ContextCompat;

public class CustomAlert extends AlertDialog {

    public CustomAlert(Activity activity) {
        super(activity);
    }

    protected Drawable setColor(int c, int d) {
        Drawable drawable = ContextCompat.getDrawable(act, d);
        if (drawable instanceof ShapeDrawable) {
            ((ShapeDrawable) drawable).getPaint().setColor(ContextCompat.getColor(act, c));
        } else if (drawable instanceof GradientDrawable) {
            ((GradientDrawable) drawable).setColor(ContextCompat.getColor(act, c));
        } else if (drawable instanceof ColorDrawable) {
            ((ColorDrawable) drawable).setColor(ContextCompat.getColor(act, c));
        }
        return drawable;
    }

    protected void checkFullAlert() {
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

    protected void checkButtons() {

        if (!isX() && !isN() && !isP()) {           // 0 0 0
            vH.setVisibility(View.GONE);
            vv1.setVisibility(View.GONE);
            vv2.setVisibility(View.GONE);
        } else if (!isX() && !isN() && isP()) {     // 0 0 1
            vH.setVisibility(View.VISIBLE);
            vv1.setVisibility(View.GONE);
            vv2.setVisibility(View.GONE);
            setBackground(backP, R.drawable.corner_leftright);
        } else if (!isX() && isN() && !isP()) {     // 0 1 0
            vH.setVisibility(View.VISIBLE);
            vv1.setVisibility(View.GONE);
            vv2.setVisibility(View.GONE);
            setBackground(backN, R.drawable.corner_leftright);
        } else if (!isX() && isN() && isP()) {      // 0 1 1
            vH.setVisibility(View.VISIBLE);
            vv1.setVisibility(View.GONE);
            vv2.setVisibility(View.VISIBLE);
            setBackground(backN, R.drawable.corner_left);
            setBackground(backP, R.drawable.corner_right);
        } else if (isX() && !isN() && !isP()) {     // 1 0 0
            vH.setVisibility(View.VISIBLE);
            vv1.setVisibility(View.GONE);
            vv2.setVisibility(View.GONE);
            setBackground(backX, R.drawable.corner_leftright);
        } else if (isX() && !isN() && isP()) {      // 1 0 1
            vH.setVisibility(View.VISIBLE);
            vv1.setVisibility(View.VISIBLE);
            vv2.setVisibility(View.GONE);
            setBackground(backX, R.drawable.corner_left);
            setBackground(backP, R.drawable.corner_right);
        } else if (isX() && isN() && !isP()) {      // 1 1 0
            vH.setVisibility(View.VISIBLE);
            vv1.setVisibility(View.VISIBLE);
            vv2.setVisibility(View.GONE);
            setBackground(backX, R.drawable.corner_left);
            setBackground(backN, R.drawable.corner_right);
        } else if (isX() && isN() && isP()) {       // 1 1 1
            vH.setVisibility(View.VISIBLE);
            vv1.setVisibility(View.VISIBLE);
            vv2.setVisibility(View.VISIBLE);
            setBackground(backX, R.drawable.corner_left);
            btnN.setBackground(makeSelector(backN));
            setBackground(backP, R.drawable.corner_right);
        }
    }

    private void setBackground(Btn b, int d) {
        b.btn.setBackground(makeSelector(b, d));
    }

    private StateListDrawable makeSelector(Btn btn, int d) {
        StateListDrawable res = new StateListDrawable();
        res.setExitFadeDuration(400);
        res.setAlpha(btn.alpha);
        res.addState(new int[]{android.R.attr.state_pressed}, setColor(btn.pressColor, d));
        res.addState(new int[]{}, setColor(btn.backColor, d));
        return res;
    }

    private StateListDrawable makeSelector(Btn btn) {
        StateListDrawable res = new StateListDrawable();
        res.setExitFadeDuration(400);
        res.setAlpha(btn.alpha);
        res.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(ContextCompat.getColor(act, btn.pressColor)));
        res.addState(new int[]{}, new ColorDrawable(ContextCompat.getColor(act, btn.backColor)));
        return res;
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
