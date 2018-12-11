package com.example.jypark.gazua;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Hunting_spot extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener{
    //my Latitude, Longitude
    double mLatitude;
    double mLongitude;
    LocationManager locationManager;
    GoogleMap GMap;
    Location locationA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hunting);

        // 위성, 지형, 일반 지도
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //GPS가 켜져있는지 체크
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //GPS 설정화면으로 이동
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        GMap = map;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.setTrafficEnabled(true);
        map.setIndoorEnabled(true);
        map.setBuildingsEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);
        // MYLocation
        map.setMyLocationEnabled(true);
        map.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                addMarker();
                return false;
            }
        });

        //------------------------------------------------

        //마시멜로 이상이면 권한 요청하기
        if (Build.VERSION.SDK_INT >= 23) {
            //권한이 없는 경우
            if (ContextCompat.checkSelfPermission(Hunting_spot.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(Hunting_spot.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Hunting_spot.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
            //권한이 있는 경우
            else {
                requestMyLocation();
            }
        }
        //마시멜로 아래
        else {
            requestMyLocation();
        }

        final Intent intent = getIntent();
        double a = intent.getDoubleExtra("x", 0);
        double b = intent.getDoubleExtra("y", 0);

        //map.google.com 마커 찍기
        final LatLng LOC = new LatLng(a, b);
/*
        map.addMarker(new MarkerOptions().position(LOC));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(LOC, 16));
*/
        /* HuntingSpot Makers Location*/
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL url;
        String response = "";

        try {
            url = new URL("http://192.168.1.86:8088/Gazua/huntingSpot.jsp");
            Log.i("url : ", "http://192.168.1.86:8088/Gazua/huntingSpot.jsp");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            Uri.Builder builder = new Uri.Builder().appendQueryParameter("", "");
            String query = builder.build().getEncodedQuery();
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);

            writer.flush();
            writer.close();
            os.close();

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                response = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[][] parseData = jsonParserList(response); //json 데이터 파싱

        for (int i = 0; i < parseData.length; i++) {
            Log.i("test", "test" + parseData[i][0]);
        }

        // HuntingSpotLoc Maker add
        double[][] HuntingSpotLoc = new double[parseData.length][2];
        for (int i = 0; i < parseData.length; i++) {
            HuntingSpotLoc[i][0] = Double.parseDouble(parseData[i][0]);
            HuntingSpotLoc[i][1] = Double.parseDouble(parseData[i][1]);

            map.addMarker(new MarkerOptions()
                    .position(new LatLng(HuntingSpotLoc[i][0], HuntingSpotLoc[i][1]))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .title(parseData[i][2]));
        }
        /*map.addMarker(new MarkerOptions()
                .position(new LatLng(mLatitude, mLongitude))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .title("나의위치"));*/

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(LOC, 9));

        /* Marker ClickListener ------------------------------------------------------------------------------------------*/
        map.setOnMarkerClickListener(this);
    }
    //------------------------------------------------------------------------------------------------------------------------
    private void addMarker() {
        GMap.addMarker(new MarkerOptions()
                .position(new LatLng(mLatitude, mLongitude))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .title("나의위치"+mLatitude+" "+mLongitude));
    }

    //받은 JSON 객체를 파싱하는 메소드-------------------------------------------------------------------------------------------
    private String[][] jsonParserList(String pRecvServerPage) {

        Log.i("서버에서 받은 전체 내용 : ", pRecvServerPage);

        try {
            JSONObject json = new JSONObject(pRecvServerPage);
            JSONArray jArr = json.getJSONArray("list");

            // 받아온 pRecvServerPage를 분석하는 부분
            String[] jsonName = {"X", "Y", "spotname", "region", "country", "point_rarity"};
            String[][] parseredData = new String[jArr.length()][jsonName.length];
            for (int i = 0; i < jArr.length(); i++) {
                json = jArr.getJSONObject(i);

                for (int j = 0; j < jsonName.length; j++) {
                    parseredData[i][j] = json.getString(jsonName[j]);
                    Log.i("print", "print" + parseredData[i][j]);
                }
            }

            // 분해 된 데이터를 확인하기 위한 부분
            for (int i = 0; i < parseredData.length; i++) {
                Log.i("JSON을 분석한 데이터 " + i + " : ", parseredData[i][0]);
                Log.i("JSON을 분석한 데이터 " + i + " : ", parseredData[i][1]);
            }
            return parseredData;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* GoogleMap Connection & MyLocation---------------------------------------------------------*/
    //나의 위치 요청
    public void requestMyLocation(){
        if(ContextCompat.checkSelfPermission(Hunting_spot.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(Hunting_spot.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        //요청
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 0, locationListener);
    }
    //위치정보 구하기 리스너
    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if(ContextCompat.checkSelfPermission(Hunting_spot.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(Hunting_spot.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                return;
            }
            //나의 위치를 한번만 가져오기 위해
            locationManager.removeUpdates(locationListener);

            //위도 경도
            mLatitude = location.getLatitude();   //위도
            mLongitude = location.getLongitude(); //경도
            Toast.makeText(Hunting_spot.this,"나의위치 : "+mLatitude+" "+mLongitude,Toast.LENGTH_SHORT).show();

            // Location A : point to point distance paramerter
            locationA = new Location("point A");
            locationA.setLatitude(mLatitude);
            locationA.setLongitude(mLongitude);
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) { Log.d("gps", "onStatusChanged"); }
        @Override
        public void onProviderEnabled(String provider) { }
        @Override
        public void onProviderDisabled(String provider) { }
    };

    //권한 요청후 응답 콜백
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //ACCESS_COARSE_LOCATION 권한
        if(requestCode==1){
            //권한받음
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                requestMyLocation();
            }
            //권한못받음
            else{
                Toast.makeText(this, "권한없음", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    /* Marker Click ------------------------------------------------------------------------------------------ */
    @Override
    public boolean onMarkerClick(Marker marker) {
        // Location B : point to point distance paramerter
        Location locationB = new Location("point B");
        locationB.setLatitude(marker.getPosition().latitude);
        locationB.setLongitude(marker.getPosition().longitude);

        double distance = locationA.distanceTo(locationB);
        String meter = Double.toString(distance);

        /* distance restrict */
        if(distance<650){
            /* Marker dialog */
            View dialogView = View.inflate(Hunting_spot.this, R.layout.activity_huntingspotmarker,null);
            AlertDialog.Builder dlg = new AlertDialog.Builder(Hunting_spot.this);
            dlg.setTitle("Marker");
            dlg.setView(dialogView);
            dlg.show();
        }

        Toast.makeText(this, "마커 위치"+"\n"+marker.getPosition().latitude+"\n"+marker.getPosition().longitude
                +"\n\n"+meter+" m", Toast.LENGTH_LONG).show();
        return false;
    }
}
