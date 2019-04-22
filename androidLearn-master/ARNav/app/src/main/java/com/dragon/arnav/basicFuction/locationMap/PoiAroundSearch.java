package com.dragon.arnav.basicFuction.locationMap;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.dragon.arnav.R;
import com.dragon.arnav.basicFuction.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * This file created by dragon on 2016/8/4 13:19,
 * belong to com.dragon.arnav.basicFuction.locationMap .
 */
public class PoiAroundSearch extends Activity implements OnClickListener,AMap.OnMapClickListener,
        AMap.OnInfoWindowClickListener,AMap.InfoWindowAdapter,AMap.OnMarkerClickListener,PoiSearch.OnPoiSearchListener {
    private MapView mapView;
    private AMap mAMap;//地图对象
    private PoiResult poiResult;//the result of the poi
    private int currentPage = 0;//the page start with 0
    private PoiSearch.Query query;//poi query
    //当前定位在深圳市南山区，这个经纬度应该是经过偏移的，具体原因，相信你懂的
    private LatLonPoint lp=new LatLonPoint(22.528402,113.938963);
    private Marker locationMarker;//选择点
    private Marker detailMarker;
    private Marker mlastMarker;
    private PoiSearch poiSearch;
    private myPoiOverlay poiOverlay;//poi图层
    private List<PoiItem> poiItems;//poi数据
    private PoiItem mPoi;

    private RelativeLayout mPoiDetail;
    private TextView mPoiName,mPoiAddress;
    private String keyWord = "";
    private EditText mSearchText;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poi_around_search);
//        基本操作
        mapView = (MapView)findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        init();
    }
//AMap对象的初始化
    private void init(){
        if(mAMap == null){
            mAMap = mapView.getMap();
//            AMap对象的监事件
            mAMap.setOnMapClickListener(this);
            mAMap.setOnMarkerClickListener(this);
            mAMap.setOnInfoWindowClickListener(this);
            mAMap.setInfoWindowAdapter(this);
//            获取相关控件
            TextView searchButton = (TextView)findViewById(R.id.btn_search);
            searchButton.setOnClickListener(this);
//初始位置标记
            locationMarker = mAMap.addMarker(new MarkerOptions().anchor(0.5f,0.5f).icon(BitmapDescriptorFactory
            .fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.point4))).position(new LatLng(lp.getLatitude(),lp.getLongitude())));
            locationMarker.showInfoWindow();
        }
        setup();
//        显示摄像头的大小
        mAMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lp.getLatitude(),lp.getLongitude()),14));
    }

    private void setup(){

        mPoiDetail = (RelativeLayout)findViewById(R.id.poi_detail);
        mPoiDetail.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
        mPoiName =(TextView)findViewById(R.id.poi_name);
        mPoiAddress = (TextView)findViewById(R.id.poi_address);
        mSearchText = (EditText)findViewById(R.id.input_edittext);
    }

//    begin poi serch
    protected void doSearchQuery(){
        //Trim() 函数的功能是去掉首尾空格
        keyWord = mSearchText.getText().toString().trim();
        currentPage = 0;
        // 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query = new PoiSearch.Query(keyWord,"","深圳市");

        query.setPageSize(20);//setting how mang itmes to return;
        query.setPageNum(currentPage);//setup query the first page;

        if(lp!=null){
            poiSearch = new PoiSearch(this,query);
            poiSearch.setOnPoiSearchListener(this);
            // 设置搜索区域为以lp点为圆心，其周围5000米范围
            poiSearch.setBound(new PoiSearch.SearchBound(lp,5000,true));
            poiSearch.searchPOIAsyn();//asyn search
        }
    }
    @Override
    protected void onResume(){
        super.onResume();
        mapView.onResume();
        whetherToShowDetailInfo(false);
    }
    @Override
    protected void onPause(){
        super.onPause();
        mapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        mapView.onDestroy();
    }
    @Override
    public void onPoiItemSearched(PoiItem arg0,int arg1){

    }
//    搜索成功时的回调
    @Override
    public void onPoiSearched(PoiResult result, int rcode){
        if(rcode ==1000){
//            检测搜索结果
            if(result!=null && result.getQuery()!=null){
//                是否是同一条
                if(result.getQuery().equals(query)){
                    poiResult = result;
                    poiItems = poiResult.getPois();
//                   获取poitem数据
                    List<SuggestionCity> suggestionCities = poiResult.getSearchSuggestionCitys();
                    if(poiItems !=null && poiItems.size()>0) {
                        //清楚POI信息
                        whetherToShowDetailInfo(false);
//                        并还原点击marker样式
                        if (mlastMarker != null) {
                            resetlastmarker();
                        }
//                        清除之前的结果marker样式
                        if (poiOverlay != null) {
                            poiOverlay.removeFromMap();
                        }
//                        新的marker
                        mAMap.clear();
                        poiOverlay = new myPoiOverlay(mAMap, poiItems);
                        poiOverlay.addToMap();
                        poiOverlay.zoomToSpan();

                        mAMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                                .icon(BitmapDescriptorFactory
                                        .fromBitmap(BitmapFactory.decodeResource(
                                                getResources(), R.drawable.point4)))
                                .position(new LatLng(lp.getLatitude(), lp.getLongitude())));
//在地图上显示搜索范围圈
                        mAMap.addCircle(new CircleOptions().center(new LatLng(lp.getLatitude(), lp.getLongitude())).radius(5000)
                                .strokeColor(Color.BLUE)
                                .fillColor(Color.argb(50, 1, 1, 1))
                                .strokeWidth(2));

                    }else if (suggestionCities !=null && suggestionCities.size()>0){
                        showSuggestCity(suggestionCities);
                    } else {
                        ToastUtil.show(PoiAroundSearch.this,R.string.no_result);
                    }
                }
            }else {
                ToastUtil.show(PoiAroundSearch.this,R.string.no_result);
            }
        }
    }


    public boolean onMarkerClick(Marker marker) {
        if (marker.getObject() != null) {
//            显示相关的位置信息
            whetherToShowDetailInfo(true);
            try {
                PoiItem mCurrentPoi = (PoiItem) marker.getObject();
                if (mlastMarker == null) {
                    mlastMarker = marker;
                } else {
//                    还原原来的marker
                    resetlastmarker();
                    mlastMarker = marker;
                }
                detailMarker = marker;
//                按下后的显示图标
                detailMarker.setIcon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.poi_marker_pressed)));
                setPoiItemDisplayContent(mCurrentPoi);
            } catch (Exception e) {

            }
        } else {
            whetherToShowDetailInfo(false);
            resetlastmarker();
        }
        return true;
    }
//    将之前点击的marker还原为原来的状态
    private void resetlastmarker() {
        int index = poiOverlay.getPoiIndex(mlastMarker);
//        10个以内的marker显示图标
        if(index<10){
            mlastMarker.setIcon(BitmapDescriptorFactory
                    .fromBitmap(BitmapFactory.decodeResource(
                            getResources(),
                            markers[index])));
        }else{
//            大于10个后的marker显示图标信息
            mlastMarker.setIcon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.marker_other_highlight)));

        }
        mlastMarker = null;
    }

    private void setPoiItemDisplayContent(final PoiItem mCurrentPoi){
        mPoiName.setText(mCurrentPoi.getTitle());
        mPoiAddress.setText(mCurrentPoi.getSnippet());
    }
    @Override
    public View getInfoContents(Marker arg0){
        return null;
    }
    @Override
    public View getInfoWindow(Marker arg0){
        return null;
    }
    @Override
    public void onInfoWindowClick(Marker arg0){

    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.btn_search:
            doSearchQuery();
            break;
            default:
                break;
        }
    }

    private int[] markers = {
            R.drawable.poi_marker_1,
            R.drawable.poi_marker_2,
            R.drawable.poi_marker_3,
            R.drawable.poi_marker_4,
            R.drawable.poi_marker_5,
            R.drawable.poi_marker_6,
            R.drawable.poi_marker_7,
            R.drawable.poi_marker_8,
            R.drawable.poi_marker_9,
            R.drawable.poi_marker_10,
    };

    private void whetherToShowDetailInfo(boolean isToShow){
        if(isToShow){
            mPoiDetail.setVisibility(View.VISIBLE);
        }else {
            mPoiDetail.setVisibility(View.GONE);
        }
    }

    @Override
    public void onMapClick(LatLng arg0){
        whetherToShowDetailInfo(false);
        if(mlastMarker!=null){
            resetlastmarker();
        }
    }

    private void showSuggestCity(List<SuggestionCity> cities){
        String infomation = "推荐城市\n";
        for(int i = 0;i<cities.size();i++){
            infomation += "城市名称：" + cities.get(i).getCityName() + "城市区号：" + cities.get(i).getCityCode() + "城市编码：" + cities.get(i).getAdCode() + "\n";
        }
        ToastUtil.show(this,infomation);
    }
//myPoiOverlay类，该类下面有多个方法
    private class myPoiOverlay{
        private AMap mamap;
        private List<PoiItem> mPois;
        private ArrayList<Marker> mPoiMarks = new ArrayList<Marker>();
//    构造函数，传进来的是amap对象和查询到的结果itmes  mPois
        public myPoiOverlay(AMap amap,List<PoiItem>pois){
            mamap = amap;
            mPois = pois;
        }

//增加Maker到地图中
    public void addToMap(){
        for(int i=0;i<mPois.size();i++){
            Marker marker = mamap.addMarker(getMarkerOptions(i));
            PoiItem item = mPois.get(i);
            marker.setObject(item);
            mPoiMarks.add(marker);
        }
    }
//移除所有的marker
    public void removeFromMap(){
        for(Marker mark: mPoiMarks){
            mark.remove();
        }
    }
//移动镜头到当前的视角
    public void zoomToSpan(){
        if(mPois !=null && mPois.size()>0){
            if(mamap ==null) return;
            LatLngBounds bounds = getLatLngBounds();
//            瞬间移动到目标位置
            mamap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds,100));
        }
    }

    private LatLngBounds getLatLngBounds(){
        LatLngBounds.Builder b = LatLngBounds.builder();
        for(int i=0;i<mPois.size();i++){
            b.include(new LatLng(mPois.get(i).getLatLonPoint().getLatitude(),mPois.get(i).getLatLonPoint().getLongitude()));
        }
        return b.build();
    }

    private MarkerOptions getMarkerOptions(int index){
        return new MarkerOptions()
                .position(new LatLng(mPois.get(index).getLatLonPoint()
                .getLatitude(),mPois.get(index)
                .getLatLonPoint().getLongitude()))
                .title(getTitle(index)).snippet(getSnippet(index))
                .icon(getBitmapDescriptor(index));

    }

    protected String getTitle(int index){
        return mPois.get(index).getTitle();
    }
    protected String getSnippet(int index){
        return mPois.get(index).getSnippet();
    }
//获取位置，第几个index就第几个poi
    public int getPoiIndex(Marker marker){
        for(int i=0;i<mPoiMarks.size();i++){
            if(mPoiMarks.get(i).equals(marker)){
                return i;
            }
        }
        return -1;
    }

    public PoiItem getPoiItem(int index) {
        if (index < 0 || index >= mPois.size()) {
            return null;
        }
        return mPois.get(index);
    }

    protected BitmapDescriptor getBitmapDescriptor(int arg0){
        if(arg0<10){
            BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap(
                    BitmapFactory.decodeResource(getResources(),markers[arg0]));
            return icon;
        }else {
            BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap(
                    BitmapFactory.decodeResource(getResources(),R.drawable.marker_other_highlight));
            return icon;
        }
    }
    }

}
