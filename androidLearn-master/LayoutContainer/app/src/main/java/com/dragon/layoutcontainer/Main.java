package com.dragon.layoutcontainer;


import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dragon.layoutcontainer.basicLayout.LinearLayout;
import com.dragon.layoutcontainer.view.FeatureView;


//添加定位接口
public final class Main extends ListActivity {
    //定义了一通用的类
    private static class DemoDetails {
        private final int titleId;
        private final int descriptionId;
        //把每个activity转成class
        private final Class<? extends android.app.Activity> activityClass;
        //    构造函数（初始化操作）
        public DemoDetails(int titleId, int descriptionId,
                           Class<? extends android.app.Activity> activityClass) {
            super();
            this.titleId = titleId;
            this.descriptionId = descriptionId;
            this.activityClass = activityClass;
        }
    }
    //ArrayAdapter(Context context, int resource, int textViewResourceId, T[] objects)
    private static class CustomArrayAdapter extends ArrayAdapter<DemoDetails> {
        //        CustomArrayAdapter中的构造函数
        public CustomArrayAdapter(Context context, DemoDetails[] demos) {
            super(context, R.layout.feature, R.id.title, demos);
        }
        //public abstract View getView (int position, View convertView, ViewGroup parent)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            FeatureView featureView;
//            convertView 对象是否是FeatureView这个特定类或者是它的子类的一个实例
            if (convertView instanceof FeatureView) {
                featureView = (FeatureView) convertView;
            } else {
//                getContext获取的是当前对象所在的Context
                featureView = new FeatureView(getContext());
            }
            DemoDetails demo = getItem(position);
            featureView.setTitleId(demo.titleId);
            featureView.setDescriptionId(demo.descriptionId);
            return featureView;
        }
    }
    //定义需要显示的列表项及描述、调用的类
    private static final DemoDetails[] demos = {
            new DemoDetails(R.string.linear_layout_demo, R.string.linear_layout_description,
                    LinearLayout.class),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setTitle("Dragon layoutContainer test" );
//        自定义CustomArrayAdapter
        CustomArrayAdapter adapter = new CustomArrayAdapter(
                this.getApplicationContext(), demos);
        setListAdapter(adapter);
    }
    //Activity 可以单独获取Back键的按下事件，此处监听回退键
    @Override
    public void onBackPressed() {
//uper.onBackPressed()是执行系统的默认动作，就是退出当前activity，所以当我们要重写这个函数时，
// 不要加super.onBackPressed()，就可以不退出activity，执行自己的代码
        super.onBackPressed();
        System.exit(0);//kill 掉当前进程。
    }
    //监听点击事件
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        DemoDetails demo = (DemoDetails) getListAdapter().getItem(position);
//        getApplicationContext() 返回应用的上下文，生命周期是整个应用，应用摧毁它才摧毁
//        Activity.this的context 返回当前activity的上下文，属于activity ，activity 摧毁他就摧毁
//        getBaseContext() 返回由构造函数指定或setBaseContext()设置的上下文
        startActivity(new Intent(this.getApplicationContext(),
                demo.activityClass));
    }
}

