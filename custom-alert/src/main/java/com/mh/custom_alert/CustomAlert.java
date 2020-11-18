package com.mh.custom_alert;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
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
