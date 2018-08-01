package com.chivorn.smarttoolbar;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Hum Vorn on 3/2/2018.
 */

public class SmartToolbar extends LinearLayout {
    private View mMainLayout;
    private LinearLayout smartToolbarLayout;
    private ViewGroup.LayoutParams mMainLayoutParams;
    private ViewGroup.LayoutParams smtbLayoutParams;
    private View vStatusBar;

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
    private final int DEFAULT_ERROR_NUM = -1;

    private final int DEFAULT_STATUS_BAR_HEIGHT = 24;

    private final String DEFAULT_TITLE_TEXT = "SampleTitleText";

    private Drawable leftBtnIcon;
    private Drawable rightBtnIcon;
    private Drawable titleIcon;
    private String titleText;

    private int toolbarBackgroundColor;
    private Drawable toolbarBackgroundDrawable;
    private int statusBackgroundColor;
    private Drawable statusBackgroundDrawable;
    private boolean isToolbarColorTypeDrawalbe;
    private boolean isStatusBarHasOwnColor;
    private boolean isInitializing = true;
    private int layoutHeight;

    public SmartToolbar(Context context) {
        this(context, null);
    }

    public SmartToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        leftBtnIcon = AppCompatResources.getDrawable(context, R.drawable.ic_arrow_back_white_24dp);
        rightBtnIcon = AppCompatResources.getDrawable(context, R.drawable.ic_close_white_24dp);
        titleIcon = AppCompatResources.getDrawable(context, R.drawable.ic_close_white_24dp);
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
        vStatusBar = findViewById(R.id.smtb_status_bar);
        smartToolbarLayout = findViewById(R.id.smtb_container);
        imgLeftBtn = findViewById(R.id.actionbar_left_btn);
        imgRightBtn = findViewById(R.id.actionbar_right_btn);
        txtTitleText = findViewById(R.id.actionbar_title);
        imgTitleIcon = findViewById(R.id.actionbar_title_image);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SmartToolbar);

        boolean isShowLeftBtn = typedArray.getBoolean(R.styleable.SmartToolbar_smtb_showLeftBtn, DEFAULT_SHOW_LEFT_BUTTON);
        setShowLeftButton(isShowLeftBtn);

        int leftBtnIconResId = typedArray.getResourceId(R.styleable.SmartToolbar_smtb_leftBtnIcon, DEFAULT_ERROR_NUM);
        if (leftBtnIconResId > DEFAULT_ERROR_NUM)
            setLeftButtonIcon(AppCompatResources.getDrawable(getContext(), leftBtnIconResId));

        boolean isShowRightBtn = typedArray.getBoolean(R.styleable.SmartToolbar_smtb_showRightBtn, DEFAULT_SHOW_RIGHT_BUTTON);
        setShowRightButton(isShowRightBtn);

        int rightBtnIconResId = typedArray.getResourceId(R.styleable.SmartToolbar_smtb_rightBtnIcon, DEFAULT_ERROR_NUM);
        if (rightBtnIconResId > DEFAULT_ERROR_NUM)
            setRightButtonIcon(AppCompatResources.getDrawable(getContext(), rightBtnIconResId));

        String titleText = typedArray.getString(R.styleable.SmartToolbar_smtb_titleText);
        setTitleText(titleText);

        int titleColor = typedArray.getInt(R.styleable.SmartToolbar_smtb_titleColor, DEFAULT_TITLE_TEXT_COLOR);
        setTitleTextColor(titleColor);

        boolean isShowTitleIcon = typedArray.getBoolean(R.styleable.SmartToolbar_smtb_showTitleIcon, false);
        setShowTitleIcon(isShowTitleIcon);

        int titleIconResId = typedArray.getResourceId(R.styleable.SmartToolbar_smtb_titleIcon, DEFAULT_ERROR_NUM);
        if (titleIconResId > DEFAULT_ERROR_NUM)
            setTitleIcon(AppCompatResources.getDrawable(getContext(), titleIconResId));

        initBackground(typedArray);
        initStatusBarColor(typedArray);

        float leftBtnIconWidth = typedArray.getDimension(R.styleable.SmartToolbar_smtb_leftBtnIconWidth, getResources().getDimension(R.dimen.default_icon_width));
        float leftBtnIconHeight = typedArray.getDimension(R.styleable.SmartToolbar_smtb_leftBtnIconHeight, getResources().getDimension(R.dimen.default_icon_height));
        updateLayoutWidth(imgLeftBtn, (int) leftBtnIconWidth);
        updateLayoutHeight(imgLeftBtn, (int) leftBtnIconHeight);

        float rightBtnIconWidth = typedArray.getDimension(R.styleable.SmartToolbar_smtb_rightBtnIconWidth, getResources().getDimension(R.dimen.default_icon_width));
        float rightBtnIconHeight = typedArray.getDimension(R.styleable.SmartToolbar_smtb_rightBtnIconHeight, getResources().getDimension(R.dimen.default_icon_height));
        updateLayoutWidth(imgRightBtn, (int) rightBtnIconWidth);
        updateLayoutHeight(imgRightBtn, (int) rightBtnIconHeight);

        float titleIconWidth = typedArray.getDimension(R.styleable.SmartToolbar_smtb_titleIconWidth, getResources().getDimension(R.dimen.default_icon_width));
        float titleIconHeight = typedArray.getDimension(R.styleable.SmartToolbar_smtb_titleIconHeight, getResources().getDimension(R.dimen.default_icon_height));
        updateLayoutWidth(imgTitleIcon, (int) titleIconWidth);
        updateLayoutHeight(imgTitleIcon, (int) titleIconHeight);

        float titleTextSize = typedArray.getDimension(R.styleable.SmartToolbar_smtb_titleTextSize, getResources().getDimension(R.dimen.default_text_size));
        setTitleTextSize(pxToDp((int) titleTextSize));

        isInitializing = false;
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
        }
    }

    @Override
    public void setBackgroundColor(int color) {
        if (!isInitializing)
            toolbarBackgroundColor = color;
        smartToolbarLayout.setBackgroundColor(color);
        if (!isStatusBarHasOwnColor && !isInitializing)
            setStatusBarColor(color);
    }

    @Override
    public void setBackground(Drawable background) {
        if (smartToolbarLayout != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                if (!isInitializing)
                    toolbarBackgroundDrawable = background;
                smartToolbarLayout.setBackground(background);
                if (!isStatusBarHasOwnColor && !isInitializing)
                    setStatusBarColor(background);
            }
        }
    }

    private void initBackground(TypedArray typedArray) {
        int drawableResId = typedArray.getResourceId(R.styleable.SmartToolbar_android_background, DEFAULT_ERROR_NUM);
        if (drawableResId > DEFAULT_ERROR_NUM) {
            isToolbarColorTypeDrawalbe = true;
            toolbarBackgroundDrawable = AppCompatResources.getDrawable(getContext(), drawableResId);
            setBackground(toolbarBackgroundDrawable);
        } else {
            isToolbarColorTypeDrawalbe = false;
            toolbarBackgroundColor = typedArray.getColor(R.styleable.SmartToolbar_android_background, DEFAULT_TOOLBAR_BACKGROUND);
            setBackgroundColor(toolbarBackgroundColor);
        }
    }

    public void setStatusBarColor(int color) {
        if (!isInitializing) {
            statusBackgroundColor = color;
            isStatusBarHasOwnColor = true;
        }
        vStatusBar.setBackgroundColor(color);
    }

    public void setStatusBarColor(Drawable color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (!isInitializing) {
                statusBackgroundDrawable = color;
                isStatusBarHasOwnColor = true;
            }
            vStatusBar.setBackground(color);
        }
    }

    private void initStatusBarColor(TypedArray typedArray) {
        int drawableResId = typedArray.getResourceId(R.styleable.SmartToolbar_smtb_statusBarColor, DEFAULT_ERROR_NUM);
        if (drawableResId > DEFAULT_ERROR_NUM) {
            isStatusBarHasOwnColor = true;
            statusBackgroundDrawable = AppCompatResources.getDrawable(getContext(), drawableResId);
            setStatusBarColor(statusBackgroundDrawable);
        } else {
            statusBackgroundColor = typedArray.getColor(R.styleable.SmartToolbar_smtb_statusBarColor, toolbarBackgroundColor);
            if (statusBackgroundColor != toolbarBackgroundColor) {
                isStatusBarHasOwnColor = true;
                setStatusBarColor(statusBackgroundColor);
            } else {
                isStatusBarHasOwnColor = false;
                if (isToolbarColorTypeDrawalbe) {
                    setStatusBarColor(toolbarBackgroundDrawable);
                }
            }
        }
    }

    public void showCustomStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            vStatusBar.setVisibility(VISIBLE);
            if (!isStatusBarHasOwnColor && isToolbarColorTypeDrawalbe) {
                setStatusBarColor(DEFAULT_TOOLBAR_BACKGROUND);
            }

            mMainLayoutParams = mMainLayout.getLayoutParams();
            smtbLayoutParams = smartToolbarLayout.getLayoutParams();

            ViewTreeObserver viewTreeObserver = mMainLayout.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            mMainLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                        layoutHeight = mMainLayout.getMeasuredHeight() + dpToPx(DEFAULT_STATUS_BAR_HEIGHT);

                        mMainLayoutParams.height = layoutHeight;
                        mMainLayout.setLayoutParams(mMainLayoutParams);

                        smtbLayoutParams.height = layoutHeight;
                        smartToolbarLayout.setLayoutParams(smtbLayoutParams);
                        invalidate();
                    }
                });
            }
        }
    }

    private void updateLayoutWidth(View view, int newWidth) {
        view.getLayoutParams().width = newWidth;
    }

    private void updateLayoutHeight(View view, int newHeight) {
        view.getLayoutParams().height = newHeight;
    }

    public void setLeftButtonIconWidth(int width) {
        updateLayoutWidth(imgLeftBtn, dpToPx(width));
    }

    public void setLeftButtonIconHeight(int height) {
        updateLayoutHeight(imgLeftBtn, dpToPx(height));
    }

    public void setRightButtonIconWidth(int width) {
        updateLayoutWidth(imgRightBtn, dpToPx(width));
    }

    public void setRightButtonIconHeight(int height) {
        updateLayoutHeight(imgRightBtn, dpToPx(height));
    }

    public void setTitleIconWidth(int width) {
        updateLayoutWidth(imgTitleIcon, dpToPx(width));
    }

    public void setTitleIconHeight(int height) {
        updateLayoutHeight(imgTitleIcon, dpToPx(height));
    }

    public void setTitleTextSize(int textSize) {
        txtTitleText.setTextSize(textSize);
    }

    public void setOnLeftButtonClickListener(OnClickListener listener) {
        imgLeftBtn.setOnClickListener(listener);
    }

    public void setOnRightButtonClickListener(OnClickListener listener) {
        imgRightBtn.setOnClickListener(listener);
    }

    private int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    private int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }
}
