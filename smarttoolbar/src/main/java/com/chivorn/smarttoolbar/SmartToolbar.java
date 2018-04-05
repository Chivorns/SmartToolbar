package com.chivorn.smarttoolbar;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Hum Vorn on 3/2/2018.
 */

public class SmartToolbar extends LinearLayout {
    private View mMainLayout;
    private LinearLayout smartToolbarLayout;

    private ImageView imgLeftBtn;
    private ImageView imgRightBtn;
    private ImageView imgTitleIcon;
    private TextView txtTitleText;

    private final boolean DEFAULT_SHOW_LEFT_BUTTON = true;
    private final boolean DEFAULT_SHOW_RIGHT_BUTTON = true;

    private final int DEFAULT_TOOLBAR_BACKGROUND = Color.parseColor("#3F51B5");
    private final int DEFAULT_TITLE_TEXT_COLOR = Color.WHITE;

    private final int DEFAULT_LEFT_BUTTON_MARGIN_LEFT = 0;
    private final int DEFAULT_LEFT_BUTTON_MARGIN_RIGHT = 0;

    private final int DEFAULT_RIGHT_BUTTON_MARGIN_LEFT = 0;
    private final int DEFAULT_RIGHT_BUTTON_MARGIN_RIGHT = 0;

    private final String DEFAULT_TITLE_TEXT = "SampleTitleText";

    private Drawable leftBtnIcon;
    private Drawable rightBtnIcon;
    private Drawable titleIcon;
    private String titleText;

    public SmartToolbar(Context context) {
        this(context, null);
    }

    public SmartToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        leftBtnIcon = context.getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp);
        rightBtnIcon = context.getResources().getDrawable(R.drawable.ic_close_white_24dp);
        titleIcon = context.getResources().getDrawable(R.drawable.ic_close_white_24dp);
        hideActionBar(context);
        init(attrs);
    }

    private void hideActionBar(Context context) {
        if (context instanceof AppCompatActivity) {
            if (((AppCompatActivity) context).getSupportActionBar() != null)
                ((AppCompatActivity) context).getSupportActionBar().hide();
        } else if (context instanceof Activity) {
            if (((Activity) context).getActionBar() != null)
                ((Activity) context).getActionBar().hide();
        }
    }

    private void init(AttributeSet attrs) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMainLayout = inflate(getContext(), R.layout.smart_toolbar_layout, this);

        smartToolbarLayout = findViewById(R.id.smtb_container);
        imgLeftBtn = findViewById(R.id.actionbar_left_btn);
        imgRightBtn = findViewById(R.id.actionbar_right_btn);
        txtTitleText = findViewById(R.id.actionbar_title);
        imgTitleIcon = findViewById(R.id.actionbar_title_image);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SmartToolbar);

        boolean isShowLeftBtn = typedArray.getBoolean(R.styleable.SmartToolbar_smtb_showLeftBtn, DEFAULT_SHOW_LEFT_BUTTON);
        setShowLeftButton(isShowLeftBtn);

        Drawable leftBtnIcon = typedArray.getDrawable(R.styleable.SmartToolbar_smtb_leftBtnIcon);
        setLeftButtonIcon(leftBtnIcon);

        boolean isShowRightBtn = typedArray.getBoolean(R.styleable.SmartToolbar_smtb_showRightBtn, DEFAULT_SHOW_RIGHT_BUTTON);
        setShowRightButton(isShowRightBtn);

        Drawable rightBtnIcon = typedArray.getDrawable(R.styleable.SmartToolbar_smtb_rightBtnIcon);
        setRightButtonIcon(rightBtnIcon);

        String titleText = typedArray.getString(R.styleable.SmartToolbar_smtb_titleText);
        setTitleText(titleText);

        int titleColor = typedArray.getInt(R.styleable.SmartToolbar_smtb_titleColor, DEFAULT_TITLE_TEXT_COLOR);
        setTitleTextColor(titleColor);

        boolean isShowTitleIcon = typedArray.getBoolean(R.styleable.SmartToolbar_smtb_showTitleIcon, false);
        setShowTitleIcon(isShowTitleIcon);

        Drawable titleIcon = typedArray.getDrawable(R.styleable.SmartToolbar_smtb_titleIcon);
        setTitleIcon(titleIcon);

        if (isTypeReference(typedArray, R.styleable.SmartToolbar_smtb_background)) {
            Drawable drawable = typedArray.getDrawable(R.styleable.SmartToolbar_smtb_background);
            setBackground(drawable);
        } else {
            int smtbBackgroundColor = typedArray.getColor(R.styleable.SmartToolbar_smtb_background, DEFAULT_TOOLBAR_BACKGROUND);
            setBackgroundColor(smtbBackgroundColor);
        }

        typedArray.recycle();
    }

    private boolean isTypeReference(TypedArray typedArray, int res) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            int type = typedArray.getType(res);
            return type == 3;
        }
        return false;
    }

    private void setViewVisibility(View view, boolean status) {
        if (status) {
            view.setVisibility(VISIBLE);
        } else {
            view.setVisibility(GONE);
        }
    }

    public void setShowLeftButton(boolean isShowLeftBtn) {
        setViewVisibility(imgLeftBtn, isShowLeftBtn);
    }

    public void setLeftButtonIcon(Drawable leftBtnIcon) {
        if (leftBtnIcon != null) {
            this.leftBtnIcon = leftBtnIcon;
            imgLeftBtn.setImageDrawable(leftBtnIcon);
        }
    }

    public void setShowRightButton(boolean isShowRightBtn) {
        setViewVisibility(imgRightBtn, isShowRightBtn);
    }

    public void setRightButtonIcon(Drawable rightBtnIcon) {
        if (rightBtnIcon != null) {
            this.rightBtnIcon = rightBtnIcon;
            imgRightBtn.setImageDrawable(rightBtnIcon);
        }
    }

    public void setTitleText(String titleText) {
        if (titleText != null) {
            this.titleText = titleText;
            this.txtTitleText.setText(titleText);
        } else {
            this.txtTitleText.setText(DEFAULT_TITLE_TEXT);
        }
    }

    public void setTitleTextColor(int color) {
        if (color != 0)
            this.txtTitleText.setTextColor(color);
        else
            this.txtTitleText.setTextColor(DEFAULT_TITLE_TEXT_COLOR);
    }

    public void setShowTitleIcon(boolean isShowTitleIcon) {
        if (isShowTitleIcon) {
            txtTitleText.setVisibility(GONE);
            imgTitleIcon.setVisibility(VISIBLE);
        } else {
            txtTitleText.setVisibility(VISIBLE);
            imgTitleIcon.setVisibility(GONE);
        }
    }

    public void setTitleIcon(Drawable titleIcon) {
        if (titleIcon != null) {
            this.titleIcon = titleIcon;
            imgTitleIcon.setImageDrawable(titleIcon);
        } else {
            imgTitleIcon.setImageDrawable(titleIcon);
        }
    }

    public void setBackgroundColor(int color) {
        smartToolbarLayout.setBackgroundColor(color);
    }


    public void setBackground(Drawable background) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            smartToolbarLayout.setBackground(background);
        }
    }

    public void setOnLeftButtonClickListener(OnClickListener listener) {
        imgLeftBtn.setOnClickListener(listener);
    }

    public void setOnRightButtonClickListener(OnClickListener listener) {
        imgRightBtn.setOnClickListener(listener);
    }
}
