package com.example.openglround2;

//import android.opengl.EGLConfig;
import javax.microedition.khronos.egl.EGLConfig;
import android.opengl.GLES10;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

import javax.microedition.khronos.opengles.GL10;

public class glsurfview extends AppCompatActivity {
    GLSurfaceView mySurfaceView;
    Dragon dragon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glsurfview);
        mySurfaceView = (GLSurfaceView)findViewById(R.id.my_surface_view);
        mySurfaceView.setEGLContextClientVersion(2);
        mySurfaceView.setRenderer(new GLSurfaceView.Renderer() {
            @Override
            public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig){
                mySurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
                try {
                    dragon = new Dragon(getApplicationContext());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSurfaceChanged(GL10 gl10,int width, int height){
                GLES20.glViewport(0, 0, width, height);
            }

            @Override
            public void onDrawFrame(GL10 gl10){
                dragon.draw();
            }
        });
    }




}
