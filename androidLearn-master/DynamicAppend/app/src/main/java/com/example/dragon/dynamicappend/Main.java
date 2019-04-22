package com.example.dragon.dynamicappend;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class Main extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View contentView  = mInflater.inflate(R.layout.main,null);
        TextView text = (TextView)contentView.findViewById(R.id.DynamicText);
        text.setText("Hello Dragon test");

        setContentView(contentView);
//        setContentView(R.layout.main);


    }
}
