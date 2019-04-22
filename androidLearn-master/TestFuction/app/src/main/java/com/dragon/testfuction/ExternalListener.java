package com.dragon.testfuction;

import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

/**
 * This file created by dragon on 2016/7/14 22:43,belong to com.dragon.testfuction .
 */
public class ExternalListener implements View.OnClickListener {
    private String str;
    public ExternalListener(String str){
        super();
        this.str=str;
    }
    public void onClick(View v){
        System.out.println(str);
    }
}
