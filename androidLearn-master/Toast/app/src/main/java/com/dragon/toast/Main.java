package com.dragon.toast;

import android.os.Handler;
import android.os.Message;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//默认显示
        Button btnshow1 = (Button) findViewById(R.id.btn1);
        btnshow1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast toast = Toast.makeText(Main.this,"默认显示", Toast.LENGTH_LONG);
                toast.show();
            }
        });
//        自定义显示位置
        Button btnshow2 =(Button) findViewById(R.id.btn2);
        btnshow2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast toast = Toast.makeText(Main.this,"自定义显示位置",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,10,-200);
                toast.show();

            }
        });

//        带图片的显示
        Button btnshow3 = (Button) findViewById(R.id.btn3);
        btnshow3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast toast = Toast.makeText(Main.this,"带图片的显示",Toast.LENGTH_SHORT);
//                获取toast的布局方式
                LinearLayout toast_layout = (LinearLayout) toast.getView();
                toast_layout.setOrientation(LinearLayout.HORIZONTAL);//设置为横向
//                创建图片视图对象
                ImageView iv = new ImageView(Main.this);
                iv.setImageResource(R.mipmap.ic_launcher);
                toast_layout.addView(iv,0);//加在文字开头
//                toast_layout.addView(iv);//加在文字结尾
                toast.show();
            }
        });
//完全自定义显示
        Button btnshow4 = (Button) findViewById(R.id.btn4);
        btnshow4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                LayoutInflater inflater = LayoutInflater.from(Main.this);
                View toast_view = inflater.inflate(R.layout.toast,null);
                Toast toast = new Toast(Main.this);
                toast.setView(toast_view);
                toast.show();
            }
        });
//在其他线程中显示
        Button btnshow5 = (Button) findViewById(R.id.btn5);
        btnshow5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        showToast();
                    }
                });
            }
        });}
        public void showToast(){
             Toast toast=Toast.makeText(getApplicationContext(), "Toast在其他线程中调用显示", Toast.LENGTH_SHORT);
             toast.show();
              }
        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                int what=msg.what;
                switch(what){
                    case 1:
                        showToast();
                        break;
                    default:
                        break;
                }
                super.handleMessage(msg);
            }
        };
}
