package com.dragon.arnav.basicFuction.poi;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.NaviPara;
import com.amap.api.maps.model.Poi;
import com.dragon.arnav.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This file created by dragon on 2016/7/19 15:15,belong to com.dragon.arnav.basicFuction.poi .
 */
public class PoiClick extends Activity implements LocationSource,AMapLocationListener,AMap.OnPOIClickListener,AMap.OnMarkerClickListener {
    private MapView mMapView;//一个显示地图的视图
    private AMap mAMap;//定义地图操作对象的接口
    private LocationSource.OnLocationChangedListener mListener;//定位监听
    private AMapLocationClient mlocationClient;//定位 客户端
    private AMapLocationClientOption mLocationOption;//定位客户端信息选择


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poiclick);
        mMapView = (MapView) findViewById(R.id.map);
//        mAMap = mMapView.getMap();//返回一个与这个视图（View）相关联的AMap 对象。
        mMapView.onCreate(savedInstanceState);
//        设置监听时间
        init();
    }

    private void init() {
        if (mAMap == null) {
            mAMap = mMapView.getMap();
            setUpMap();
        }
        mAMap.setOnPOIClickListener(this);//地图底图poi选中回调 此接口的调用是在主线程中。
        mAMap.setOnMarkerClickListener(this);//定义了当marker 对象被点击时回调的接口。
    }

    private void setUpMap() {

        mAMap.setLocationSource(this);// 设置定位监听
        mAMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        mAMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        // 设置定位的类型为定位模式 ，可以由定位、跟随或地图根据面向方向旋转几种
        mAMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_FOLLOW);//定位模式
    }
//地图POI点击回调
    @Override
    public void onPOIClick(Poi poi){
        mAMap.clear();//从地图上删除所有的Marker，Overlay，Polyline 等覆盖物。
        MarkerOptions markerOptions = new MarkerOptions();
//        获取坐标
        markerOptions.position(poi.getCoordinate());
//        下面就是用来显示一个图标
        TextView textView = new TextView(getApplicationContext());
        textView.setText("到"+poi.getName()+"去");
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLACK);
        textView.setBackgroundResource(R.drawable.custom_info_bubble);
        markerOptions.icon(BitmapDescriptorFactory.fromView(textView));
        mAMap.addMarker(markerOptions);
    }
//    点击Marker图标后的执行的操作
    @Override
    public boolean onMarkerClick(Marker marker){
//        构造导航参数
        NaviPara naviPara = new NaviPara();
//        设置终点位置
        naviPara.setTargetPoint(marker.getPosition());
//        设置导航策略，这里是避免拥堵
        naviPara.setNaviStyle(AMapUtils.DRIVING_AVOID_CONGESTION);
        try{
//            这里调用高德地图来导航
            AMapUtils.openAMapNavi(naviPara,getApplicationContext());
        } catch (com.amap.api.maps.AMapException e){
//            如果你没安装，这会触发这个异常
            AMapUtils.getLatestAMapApp(getApplicationContext());
        }
        mAMap.clear();
        return false;
    }

//    下面这些没什么要说的
    @Override
    protected void onResume(){
        super.onResume();
        mMapView.onResume();
    }
    @Override
    protected void onPause(){
        super.onPause();
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        mMapView.onDestroy();
    }


    /**
     * 定位成功后回调函数
     */
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation != null
                    && amapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                amapLocation.getLatitude();//获取纬度
                amapLocation.getLongitude();//获取经度
                amapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amapLocation.getTime());
                df.format(date);//定位时间
                amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                amapLocation.getCountry();//国家信息
                amapLocation.getProvince();//省信息
                amapLocation.getCity();//城市信息
                amapLocation.getDistrict();//城区信息
                amapLocation.getStreet();//街道信息
                amapLocation.getStreetNum();//街道门牌号信息
                amapLocation.getCityCode();//城市编码
                amapLocation.getAdCode();//地区编码
                Log.e("information",amapLocation.getAddress());
                Log.e("lati+long",amapLocation.getLatitude()+","+amapLocation.getLongitude());



            } else {
                Log.e("AmapError","location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }

    /**
     * 激活定位
     */
    @Override
    public void activate(LocationSource.OnLocationChangedListener listener) {
        mListener = listener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(this);
//            初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位间隔,单位毫秒,默认为2000ms
            mLocationOption.setInterval(2000);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
    }

    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }
}
