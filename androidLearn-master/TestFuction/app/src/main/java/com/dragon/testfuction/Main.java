package com.dragon.testfuction;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


public class Main extends AppCompatActivity  {
    static final String UPPER_NUM = "upper";
    EditText etNum;
    CalThread calThread;
//    定义一个线程类
    class CalThread extends Thread {
            public Handler mHandler;
            public void run(){
//                创建looper对象，每一个线程使用Handle都要有一个looper对象
                Looper.prepare();
//                子线程中定义handler获取处理消息
                mHandler = new Handler(){
//                    定义处理信息的方法
                    @Override
                    public void handleMessage(Message msg){
                            if(msg.what == 0x123){
                                int upper = msg.getData().getInt(UPPER_NUM);
                                List<Integer> nums = new ArrayList<Integer>();
                                outer:
//                                质数也是素数，除了1和它本身外，不能被其它整除
                                for(int i =2;i <=upper;i++){
                                    for(int j=2; j<= Math.sqrt(i);j++){
//                                        如果可以整除，说明不是质数
                                        if(i!=2 && i%j==0){
                                            continue outer;
                                        }
                                    }
                                    nums.add(i);
                                }
//                                用Toast显示所有统计出来的质数
                                Toast.makeText(Main.this,nums.toString(),Toast.LENGTH_LONG).show();
                            }
                    }
                };
                Looper.loop();//启动looper
            }
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        etNum = (EditText) findViewById(R.id.etNum);
        calThread = new CalThread();
        calThread.start();//启动新线程
    }
//按钮事件点击处理函数
    public void cal(View source){
//        创建消息
        Message msg = new Message();
        msg.what = 0x123;
        Bundle bundle = new Bundle();
        bundle.putInt(UPPER_NUM,Integer.parseInt(etNum.getText().toString()));
        msg.setData(bundle);
//        在主线程中想新线程中的Handler发送消息
        calThread.mHandler.sendMessage(msg);//在主线程中发送消息
    }
}
