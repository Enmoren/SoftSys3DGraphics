package edu.wuwang.opengl.obj;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import edu.wuwang.opengl.BaseActivity;
import edu.wuwang.opengl.R;
import edu.wuwang.opengl.utils.Gl2Utils;

/**
 * Created by wuwang on 2017/1/7
 */

public class ObjLoadActivity extends BaseActivity {

    private GLSurfaceView mGLView;
    private ObjFilter mFilter;
    private List<ObjFilter2> filters;
    private Obj3D obj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obj);
        mGLView= (GLSurfaceView) findViewById(R.id.mGLView);
        mGLView.setEGLContextClientVersion(2);
        mFilter=new ObjFilter(getResources());
//        obj=new Obj3D();
//        try {
            List<Obj3D> model=ObjReader.readMultiObj(this,"3dres/dragon.obj");
            filters=new ArrayList<>();
            for (int i=0;i<model.size();i++){
                ObjFilter2 f=new ObjFilter2(getResources());
                f.setObj3D(model.get(i));
                filters.add(f);
            }
//            ObjReader.read(getAssets().open("3dres/model.obj"),obj);
//            mFilter.setObj3D(obj);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        mGLView.setRenderer(new GLSurfaceView.Renderer() {
//            @Override
//            public void onSurfaceCreated(GL10 gl, EGLConfig config) {
//                mFilter.create();
//            }
//
//            @Override
//            public void onSurfaceChanged(GL10 gl, int width, int height) {
//                mFilter.onSizeChanged(width, height);
//                float[] matrix=Gl2Utils.getOriginalMatrix();
//                Matrix.scaleM(matrix,0,0.2f,0.2f*width/height,0.2f);
//                mFilter.setMatrix(matrix);
//            }
//
//            @Override
//            public void onDrawFrame(GL10 gl) {
//                Matrix.rotateM(mFilter.getMatrix(),0,0.3f,0,1,0);
//                mFilter.draw();
//            }
//        });
        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            for (ObjFilter2 f:filters){
                f.create();
            }
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            for (ObjFilter2 f:filters){
                f.onSizeChanged(width, height);
                float[] matrix= Gl2Utils.getOriginalMatrix();
                Matrix.translateM(matrix,0,0,-0.3f,0);
                Matrix.scaleM(matrix,0,0.008f,0.008f*width/height,0.008f);
                f.setMatrix(matrix);
            }
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
            for (ObjFilter2 f:filters){
                Matrix.rotateM(f.getMatrix(),0,0.3f,0,1,0);
                f.draw();
            }
        }
    });
        mGLView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mGLView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLView.onPause();
    }
}
