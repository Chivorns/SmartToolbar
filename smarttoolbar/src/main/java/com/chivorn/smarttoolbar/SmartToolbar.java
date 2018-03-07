package com.chivorn.smarttoolbar;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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

    private ImageView imgLeftBtn;
    private ImageView imgRightBtn;
    private ImageView imgTitleIcon;
    private TextView txtTitleText;

    private boolean DEFAULT_SHOW_LEFT_BUTTON = true;
    private boolean DEFAULT_SHOW_RIGHT_BUTTON = true;
    private int DEFAULT_TITLE_BAR_COLOR = Color.WHITE;

    private int DEFAULT_LEFT_BUTTON_MARGIN_LEFT;
    private int DEFAULT_LEFT_BUTTON_MARGIN_RIGHT;

    private int DEFAULT_RIGHT_BUTTON_MARGIN_LEFT;
    private int DEFAULT_RIGHT_BUTTON_MARGIN_RIGHT;

    private int DEFAULT_TOOLBAR_BACKGROUND;

    private Drawable leftBtnIcon;
    private Drawable rightBtnIcon;
    private Drawable titleIcon;
    private String titleText = "SampleTitleText";

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

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SmartToolbar);
        imgLeftBtn = findViewById(R.id.actionbar_left_btn);
        imgRightBtn = findViewById(R.id.actionbar_right_btn);
        txtTitleText = findViewById(R.id.actionbar_title);
        imgTitleIcon = findViewById(R.id.actionbar_title_image);

        boolean isShowLeft = typedArray.getBoolean(R.styleable.SmartToolbar_smtb_showLeftBtn, DEFAULT_SHOW_LEFT_BUTTON);
        if (!isShowLeft) {
            imgLeftBtn.setVisibility(GONE);
        }

        Drawable newLeftIcon = typedArray.getDrawable(R.styleable.SmartToolbar_smtb_leftBtnIcon);
        if (newLeftIcon != null)
            leftBtnIcon = newLeftIcon;
        imgLeftBtn.setImageDrawable(leftBtnIcon);

        boolean isShowRight = typedArray.getBoolean(R.styleable.SmartToolbar_smtb_showRightBtn, DEFAULT_SHOW_RIGHT_BUTTON);
        if (!isShowRight) {
            imgRightBtn.setVisibility(GONE);
        }

        Drawable newRightIcon = typedArray.getDrawable(R.styleable.SmartToolbar_smtb_rightBtnIcon);
        if (newRightIcon != null)
            rightBtnIcon = newRightIcon;
        imgRightBtn.setImageDrawable(rightBtnIcon);

        String titleText = typedArray.getString(R.styleable.SmartToolbar_smtb_titleText);
        if (titleText == null)
            titleText = this.titleText;
        this.txtTitleText.setText(titleText);
        this.txtTitleText.setTextColor(typedArray.getInt(R.styleable.SmartToolbar_smtb_titleColor, DEFAULT_TITLE_BAR_COLOR));

        boolean isShowTitleImage = typedArray.getBoolean(R.styleable.SmartToolbar_smtb_showTitleImage, false);

        if (isShowTitleImage) {
            this.txtTitleText.setVisibility(GONE);
            imgTitleIcon.setVisibility(VISIBLE);

            Drawable newTitleIcon = typedArray.getDrawable(R.styleable.SmartToolbar_smtb_titleIcon);
            if (newTitleIcon != null)
                titleIcon = newTitleIcon;
            imgTitleIcon.setImageDrawable(titleIcon);
        }
    }

    public void setOnLeftButtonClickListener(OnClickListener listener) {
        imgLeftBtn.setOnClickListener(listener);
    }

    public void setOnRightButtonClickListener(OnClickListener listener) {
        imgRightBtn.setOnClickListener(listener);
    }
}
