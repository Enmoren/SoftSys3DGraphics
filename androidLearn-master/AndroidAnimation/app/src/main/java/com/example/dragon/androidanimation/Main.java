package com.example.dragon.androidanimation;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

public class Main extends Activity {

    private SeekBar seekBarX;// 拖动条控件
    private SeekBar seekBarY;
    private SeekBar scaleSeekBarX;
    private SeekBar scaleSeekBarY;
    private SeekBar rotationSeekBarX;
    private SeekBar rotationSeekBarY;
    private SeekBar rotationSeekBarZ;
    private ImageView mImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initViews();
        initEvents();
    }

    /**
     *
     * @description：初始化控件
     * @author ldm
     * @date 2016-6-22 下午5:26:26
     */
    private void initViews() {
        mImageView = (ImageView) findViewById(R.id.imageView);
        seekBarX = (SeekBar) findViewById(R.id.translationX);
        seekBarX.setMax(400);
        seekBarY = (SeekBar) findViewById(R.id.translationY);
        seekBarY.setMax(800);

        scaleSeekBarX = (SeekBar) findViewById(R.id.scaleX);
        scaleSeekBarX.setMax(50);
        scaleSeekBarX.setProgress(10);
        scaleSeekBarY = (SeekBar) findViewById(R.id.scaleY);
        scaleSeekBarY.setMax(50);
        scaleSeekBarY.setProgress(10);

        rotationSeekBarX = (SeekBar) findViewById(R.id.rotationX);
        rotationSeekBarX.setMax(360);
        rotationSeekBarY = (SeekBar) findViewById(R.id.rotationY);
        rotationSeekBarY.setMax(360);
        rotationSeekBarZ = (SeekBar) findViewById(R.id.rotationZ);
        rotationSeekBarZ.setMax(360);
    }

    /**
     *
     * @description：控件设置监听事件
     * @author ldm
     * @date 2016-6-22 下午5:26:26
     */
    private void initEvents() {
        // 按钮X方向平移动画
        seekBarX.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // X方向平移
                mImageView.setTranslationX((float) progress);
            }
        });
        // 按钮Y方向平移动画
        seekBarY.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // Y方向平移
                mImageView.setTranslationY((float) progress);
            }
        });
        // 按钮X方向缩放动画
        scaleSeekBarX
                .setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }

                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    public void onProgressChanged(SeekBar seekBar,
                                                  int progress, boolean fromUser) {
                        // X方向缩放
                        mImageView.setScaleX((float) progress / 10f);
                    }
                });
        // 按钮Y方向缩放动画
        scaleSeekBarY
                .setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }

                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    public void onProgressChanged(SeekBar seekBar,
                                                  int progress, boolean fromUser) {
                        // Y方向缩放
                        mImageView.setScaleY((float) progress / 10f);
                    }
                });
        // 按钮X方向旋转动画
        rotationSeekBarX
                .setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }

                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    public void onProgressChanged(SeekBar seekBar,
                                                  int progress, boolean fromUser) {
                        // X方向旋转
                        mImageView.setRotationX((float) progress);
                    }
                });
        // 按钮Y方向旋转动画
        rotationSeekBarY
                .setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }

                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    public void onProgressChanged(SeekBar seekBar,
                                                  int progress, boolean fromUser) {
                        // Y方向旋转
                        mImageView.setRotationY((float) progress);
                    }
                });
        // 按钮Z方向旋转动画
        rotationSeekBarZ
                .setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }

                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    public void onProgressChanged(SeekBar seekBar,
                                                  int progress, boolean fromUser) {
                        // 设置旋转
                        mImageView.setRotation((float) progress);
                    }
                });
    }
}
