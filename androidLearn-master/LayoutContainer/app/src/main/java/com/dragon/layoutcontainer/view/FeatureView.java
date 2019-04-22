package com.dragon.layoutcontainer.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dragon.layoutcontainer.R;


/**
 * This file created by dragon on 2016/7/19 9:26,belong to com.dragon.arnav.basicFuction.view .
 */
public class FeatureView extends FrameLayout {
    public FeatureView(Context context){
        super(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.feature,this);
    }
    public synchronized void setTitleId(int titleId){
        ((TextView)(findViewById(R.id.title))).setText(titleId);
    }

    public synchronized void setDescriptionId(int descriptionId){
        ((TextView)(findViewById(R.id.description))).setText(descriptionId);
    }
}
