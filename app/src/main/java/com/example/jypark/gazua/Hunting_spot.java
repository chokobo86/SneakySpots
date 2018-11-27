package com.example.jypark.gazua;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Hunting_spot extends AppCompatActivity implements OnMapReadyCallback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hunting_spot);

        // 위성, 지형, 일반 지도
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.setMyLocationEnabled(true);
        map.setTrafficEnabled(true);
        map.setIndoorEnabled(true);
        map.setBuildingsEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);

        final Intent intent = getIntent();
        double a = intent.getDoubleExtra("x",0);
        double b = intent.getDoubleExtra("y",0);

        //map.google.com 마커 찍기
        final LatLng LOC = new LatLng(a, b);
/*
        map.addMarker(new MarkerOptions().position(LOC));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(LOC, 16));

        map.addMarker(
                new MarkerOptions()
                        .position(LOC)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                        //.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
                        .title("커피향기")
                        .snippet("아메리카노+프레즐"));
        */
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(LOC, 9));
    }

}
