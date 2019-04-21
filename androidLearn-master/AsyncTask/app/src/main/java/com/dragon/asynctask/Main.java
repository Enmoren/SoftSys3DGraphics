package com.dragon.asynctask;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main extends AppCompatActivity {
    private final String TAG="asynctask";
    private ImageView mImageView;
    private Button mButton;
    private ProgressDialog mDialog;
//    the path of image
    private String mImagePath="http://g.hiphotos.baidu.com/image/h%3D360/sign=4df699dff536afc3110c39638318eb85/908fa0ec08fa513d682eb8c13f6d55fbb2fbd92d.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//        add Dialog
        mDialog = new ProgressDialog(this);
//        this dialog will never occur,if your network is good.
        mDialog.setTitle("attention");
        mDialog.setMessage("waiting ... ...");

        mImageView = (ImageView)findViewById(R.id.image);
//        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);//when picture doesn't fit your phone,if can use this.
        mButton = (Button) findViewById(R.id.button);
//        listener
        mButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
//                new async task
                DownTask task = new DownTask();
//                must be execute int the UI thread also called Main thread.
                task.execute(mImagePath);
            }
        });
    }
//    AsyncTask provided by google.
    public class DownTask extends AsyncTask<String,Void,Bitmap> {
        @Override
        protected void onPreExecute(){
            mDialog.show();
        }
//        the params is variable
        protected Bitmap doInBackground(String ... params){
            URL imageUrl = null;
            Bitmap mBitmap=null;
            InputStream inputData=null;
            HttpURLConnection urlConn=null;
            try{
                imageUrl = new URL(params[0]);
            }catch (MalformedURLException e){
                e.printStackTrace();
            }
            //            get the net data using httpclient.
            try {
                urlConn =(HttpURLConnection) imageUrl.openConnection();
                urlConn.setDoInput(true);
                urlConn.connect();
//                convert  to inputStream
                inputData = urlConn.getInputStream();
//                decode
                mBitmap = BitmapFactory.decodeStream(inputData);
                inputData.close();
            }catch(IOException e){
                Log.e(TAG,e.getMessage());
            }finally{
                try {
                    if(inputData != null){
                        inputData.close();
                    }
                    if( urlConn != null){
                        urlConn.disconnect();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return mBitmap;

        }
//    when doinbackground is over, next thing we should do.
        @Override
        protected void onPostExecute(Bitmap result){
            super.onPostExecute(result);
//            show picture in the UI view.
            mImageView.setImageBitmap(result);
//            disable this dialog.
            mDialog.dismiss();
//            let the button invisible, so we can see more comfortable, you konw.
            mButton.setVisibility(View.INVISIBLE);
        }
    }
}
