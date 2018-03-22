package com.chivorn.smarttoolbar.demojava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chivorn.smarttoolbar.SmartToolbar;

public class MainActivity extends AppCompatActivity {

    SmartToolbar smartToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smartToolbar = findViewById(R.id.toolbar);
        smartToolbar.setTitleText("New Title Text");
    }
}
