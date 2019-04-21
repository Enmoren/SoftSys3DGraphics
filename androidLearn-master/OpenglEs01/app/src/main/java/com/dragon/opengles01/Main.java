package com.dragon.opengles01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.dragon.opengles01.ndk.NDKTest;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView textView = (TextView) findViewById(R.id.ndk_test);
        String str = NDKTest.hello();
        textView.setText(str);
    }

}
