package com.dragon.opengles02;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Main extends AppCompatActivity {
    private GLSurfaceView mGLView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
//        define a GlSurfaceView 容器，用于显示OpenGL的图形
        mGLView = new GLSurfaceView(this);
//        创建GLSurfaceView绘制器
        MyRenderer myRenderer = new MyRenderer();
        mGLView.setRenderer(myRenderer);
        //只有在数据改变时才进行Render，这样来提高性能
//        mGLView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        setContentView(mGLView);
    }
}
