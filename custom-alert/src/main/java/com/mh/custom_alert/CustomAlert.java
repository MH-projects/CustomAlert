package com.mh.custom_alert;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.text.Html;
import android.util.TypedValue;
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

public class CustomAlert implements View.OnClickListener {

    private Context ctx;

    private View view;
    private Dialog dialog;

    private RelativeLayout relAlert;
    private ImageView ivCircle;
    private TextView tvTitle;
    private TextView tvMessage;
    private LinearLayout llView;
    private LinearLayout llButtons;
    private TextView btnNeutral;
    private TextView btnNegative;
    private TextView btnPositive;

    private boolean setMarginTop;
    private boolean fullAlert = false;

    @SuppressLint("InflateParams")
    public CustomAlert(Context context) {

        ctx = context;

        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = Objects.requireNonNull(inflater).inflate(R.layout.custom_alert, null);

        relAlert = view.findViewById(R.id.relAlert);
        ivCircle = view.findViewById(R.id.circleView);
        tvTitle = view.findViewById(R.id.tvTitle);
        tvMessage = view.findViewById(R.id.tvMessage);
        llView = view.findViewById(R.id.llView);
        llButtons = view.findViewById(R.id.llButtons);
        btnNeutral = view.findViewById(R.id.btnNeutral);
        btnNegative = view.findViewById(R.id.btnNegative);
        btnPositive = view.findViewById(R.id.btnPositive);

        dialog = new Dialog(ctx);
        view.findViewById(R.id.ivClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                setColor(R.color.colorAccentCustomAlert);
                /*TODO ((ImageView) custom_view_identity.findViewById(R.id.circleView)).setImageDrawable(null);
                ((ImageView) custom_view_identity.findViewById(R.id.close)).setImageDrawable(null);
                custom_view_identity.findViewById(R.id.buttonLeft).setBackground(null);
                custom_view_identity.findViewById(R.id.buttonRight).setBackground(null);*/
            }
        });
    }

    public void setCancelable(boolean cancelable) {
        dialog.setCancelable(cancelable);
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

    public void setView(View viewCustom) {
        if (viewCustom == null) {
            llView.removeAllViews();
            llView.setVisibility(View.GONE);
        } else {
            llView.addView(viewCustom);
            llView.setVisibility(View.VISIBLE);
        }
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, R.id.tvMessage);
        view.findViewById(R.id.scrollView).setLayoutParams(params);
    }

    public void setHeight(final int height) {

        final ScrollView layout = view.findViewById(R.id.scrollView);
        final ViewGroup.LayoutParams params = layout.getLayoutParams();
        ViewTreeObserver vto = layout.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                layout.getViewTreeObserver().removeOnPreDrawListener(this);
                int finalHeight = layout.getMeasuredHeight();
                if ((finalHeight + 250) > (height - 100)) {
                    params.height = height - 400;
                    layout.setLayoutParams(params);
                }
                return true;
            }
        });
    }

    public void setButton(String text, int color, View.OnClickListener listener) {
        setBtn(text, color, listener);
    }

    public void setButton(String text, View.OnClickListener listener) {
        setBtn(text, ctx.getResources().getColor(R.color.colorAccentCustomAlert), listener);
    }

    public void setButtons(String textNegative, View.OnClickListener listenerNegative,
                           String textPositive, View.OnClickListener listenerPositive) {
        setBtns(textNegative, ctx.getResources().getColor(R.color.colorFailCustomAlert), listenerNegative,
                textPositive, ctx.getResources().getColor(R.color.colorSuccessCustomAlert), listenerPositive);
    }

    public void setButtons(String textNegative, int colorNegative, View.OnClickListener listenerNegative,
                           String textPositive, int colorPositive, View.OnClickListener listenerPositive) {
        setBtns(textNegative, colorNegative, listenerNegative, textPositive, colorPositive, listenerPositive);
    }

    private void setBtn(String t, int c, View.OnClickListener l) {
        relAlert.setBackgroundResource(R.drawable.corner_back_top);
        llButtons.setVisibility(View.VISIBLE);
        btnNeutral.setVisibility(View.VISIBLE);
        view.findViewById(R.id.llNegativePositive).setVisibility(View.GONE);
        btnNeutral.setText(t);
        btnNeutral.setTextColor(c);
        btnNeutral.setOnClickListener(l);
    }

    private void setBtns(String tn, int cn, View.OnClickListener ln,
                         String tp, int cp, View.OnClickListener lp) {
        btnNegative.setText(tn);
        btnNegative.setTextColor(cn);
        btnNegative.setOnClickListener(ln);
        btnPositive.setText(tp);
        btnPositive.setTextColor(cp);
        btnPositive.setOnClickListener(lp);
        relAlert.setBackgroundResource(R.drawable.corner_back_top);
        llButtons.setVisibility(View.VISIBLE);
        btnNeutral.setVisibility(View.GONE);
        view.findViewById(R.id.llNegativePositive).setVisibility(View.VISIBLE);
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

    public void setColor(int color) {
        Drawable background = ivCircle.getBackground();
        if (background instanceof ShapeDrawable) {
            ((ShapeDrawable) background).getPaint().setColor(ContextCompat.getColor(ctx, color));
        } else if (background instanceof GradientDrawable) {
            ((GradientDrawable) background).setColor(ContextCompat.getColor(ctx, color));
        } else if (background instanceof ColorDrawable) {
            ((ColorDrawable) background).setColor(ContextCompat.getColor(ctx, color));
        }
    }

    public void setBackgroundNeutralButton(int color, int alpha) {
        btnNeutral.setBackground(makeSelector(ctx.getResources().getColor(color), alpha));
    }

    public void setBackgroundNegativeButton(int color, int alpha) {
        btnNegative.setBackground(makeSelector(ctx.getResources().getColor(color), alpha));
    }

    public void setBackgroundPositiveButton(int color, int alpha) {
        btnPositive.setBackground(makeSelector(ctx.getResources().getColor(color), alpha));
    }

    private StateListDrawable makeSelector(int c, int a) {
        StateListDrawable res = new StateListDrawable();
        res.setExitFadeDuration(400);
        res.setAlpha(a);
        res.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(c));
        res.addState(new int[]{}, new ColorDrawable(Color.TRANSPARENT));
        return res;
    }

    public void setIcon(Integer icon) {
        if (icon == null) {
            ivCircle.setVisibility(View.GONE);
        } else {
            ivCircle.setImageResource(icon);
        }
        setMarginTop = icon != null;
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

    private void checkFullAlert() {
        if (fullAlert) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, 0);
            view.findViewById(R.id.relAlert).setLayoutParams(params);
            if (setMarginTop) {
                int marginInDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, ctx.getResources().getDisplayMetrics());
                RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0);
                params2.setMargins(0, marginInDp, 0, 0);
                view.findViewById(R.id.view).setLayoutParams(params2);
            }
        }
    }

    public void show() {

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;

        checkFullAlert();

        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    @Override
    public void onClick(View v) {

    }
}
