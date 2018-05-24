package com.chivorn.smarttoolbar.demojava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.chivorn.smarttoolbar.SmartToolbar;

public class MainActivity extends AppCompatActivity {

    private SmartToolbar smartToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smartToolbar = findViewById(R.id.toolbar);

        // TODO: setTitleText
        // smartToolbar.setTitleText("New Title Text");

        // TODO: setTitleTextColor
        // smartToolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));
        // smartToolbar.setTitleTextColor(Color.YELLOW);

        // TODO: setShowTitleIcon & setTitleIcon
        // smartToolbar.setShowTitleIcon(true);
        // smartToolbar.setTitleIcon(getResources().getDrawable(R.drawable.ic_close_white_24dp));

        // TODO: setBackgroundColor & setBackgroundDrawable
        // smartToolbar.setBackground(getResources().getDrawable(R.drawable.smart_toolbar_bg_gradient));
        // smartToolbar.setBackgroundColor(Color.YELLOW);

        // TODO: setVisibility of left and right button
        // smartToolbar.setShowLeftButton(true);
        // smartToolbar.setShowRightButton(true);

        // TODO: setIcon to left and right button
        // smartToolbar.setLeftButtonIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));
        // smartToolbar.setRightButtonIcon(getResources().getDrawable(R.drawable.ic_close_white_24dp));

        // TODO: set background color
        // smartToolbar.setBackground(getResources().getDrawable(R.drawable.smart_toolbar_bg_gradient));
        // smartToolbar.setBackgroundColor(Color.BLUE);

        // TODO: show custom status bar
        smartToolbar.showCustomStatusBar(this);

        // TODO: set status bar color
        // smartToolbar.setStatusBarColor(getResources().getDrawable(R.drawable.smart_toolbar_bg_gradient));
        // smartToolbar.setStatusBarColor(Color.BLUE);
    }
}
