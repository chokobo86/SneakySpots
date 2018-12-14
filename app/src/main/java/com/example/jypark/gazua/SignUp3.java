package com.example.jypark.gazua;

import android.app.Dialog;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

public class SignUp3 extends DialogFragment implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener, GoogleMap.OnMapClickListener {
    Geocoder geocoder;
    SupportMapFragment mapFragment;
    GoogleMap googleMap;
    HashMap<String,String> map = null;
    EditText searchAddr;
    Button searchBtn;
    View view = null;
    String location, sX, sY;
    MarkerOptions markerOptions = new MarkerOptions();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapFragment = (SupportMapFragment)getFragmentManager().findFragmentById(R.id.signUpMap);
        if (mapFragment != null) {
            getFragmentManager().beginTransaction().remove(mapFragment).commit();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        view = inflater.inflate(R.layout.activity_signup3, null);
        searchAddr = view.findViewById(R.id.searchAddr);
        searchBtn = view.findViewById(R.id.searchBtn);
        mapFragment = (SupportMapFragment)getFragmentManager().findFragmentById(R.id.signUpMap);
        mapFragment.getMapAsync(this);
        geocoder = new Geocoder(getContext());

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location = searchAddr.getText().toString();
                searchLocation(location);
            }
        });

        dlg.setTitle("SIGN UP");
        dlg.setIcon(R.drawable.ic_menu_allfriends);

        dlg.setView(view)
                .setPositiveButton("완료", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        insertUser(map);
                        insertUserStatus(map);
                    }
                })
                .setNegativeButton("이전", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DialogFragment dialog2 = new SignUp2();
                        ((SignUp2) dialog2).getHashMap(map);
                        dialog2.show(getFragmentManager(),"dialog2");
                    }
                });

        return dlg.create();
    }

    public void getHashMap(HashMap<String,String> map){
        this.map = map;
    }

    public String insertUser(HashMap map){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL url;
        String response = "";

        try {
            url = new URL("http://192.168.1.86:8088/Gazua/insertUser.jsp");
            Log.i("url : ","http://192.168.1.86:8088/Gazua/insertUser.jsp");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("email", (String) map.get("email"))
                    .appendQueryParameter("password", (String) map.get("password"))
                    .appendQueryParameter("nickname", (String) map.get("nickname"))
                    .appendQueryParameter("nationality", (String) map.get("nationality"))
                    .appendQueryParameter("detail", (String) map.get("detail"))
                    .appendQueryParameter("photo", (String) map.get("photo"))
                    .appendQueryParameter("x", (String) map.get("X"))
                    .appendQueryParameter("y", (String) map.get("Y"));
            String query = builder.build().getEncodedQuery();
            Log.i("query : ", query);
            Log.d("query : ", query);
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);

            writer.flush();
            writer.close();
            os.close();

            int responseCode=conn.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                System.out.println("성공");
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response += line;
                }
            }
            else {
                response = "";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    public String insertUserStatus(HashMap map){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL url;
        String response = "";

        try {
            url = new URL("http://192.168.1.86:8088/Gazua/insertUserStatus.jsp");
            Log.i("url : ","http://192.168.1.86:8088/Gazua/insertUser.jsp");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("nickname", (String) map.get("nickname"))
                    .appendQueryParameter("loc_x", (String) map.get("X"))
                    .appendQueryParameter("loc_y", (String) map.get("Y"));
            String query = builder.build().getEncodedQuery();
            Log.i("query : ", query);
            Log.d("query : ", query);
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);

            writer.flush();
            writer.close();
            os.close();

            int responseCode=conn.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                System.out.println("성공");
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response += line;
                }
            }
            else {
                response = "";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.setTrafficEnabled(true);
        googleMap.setIndoorEnabled(true);
        googleMap.setBuildingsEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setOnMarkerDragListener(this);
        googleMap.setOnMapClickListener(this);
        searchNationality(map.get("nationality"));
    }

    public void searchNationality(String searchStr){
        // 결과값이 들어갈 리스트 선언
        List<Address> addressList = null;

        try {
            addressList = geocoder.getFromLocationName(searchStr, 3);

            if (addressList != null) {
                Address outAddr = addressList.get(0);
                LatLng curPoint = new LatLng(outAddr.getLatitude(),
                        outAddr.getLongitude());

                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint,
                        5));
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        } catch (IOException ex) {
            Log.d("예외", "예외 : " + ex.toString());
        }
    }

    public void searchLocation(String searchStr) {
        // 결과값이 들어갈 리스트 선언
        List<Address> addressList = null;

        try {
            addressList = geocoder.getFromLocationName(searchStr, 3);

            if (addressList != null) {
                Address outAddr = addressList.get(0);
                sX = String.valueOf(outAddr.getLatitude());
                sY = String.valueOf(outAddr.getLongitude());
                map.put("X",sX);
                map.put("Y",sY);
                LatLng curPoint = new LatLng(outAddr.getLatitude(),
                        outAddr.getLongitude());

                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint,
                        15));

                // 지도 유형 설정. 지형도인 경우에는 GoogleMap.MAP_TYPE_TERRAIN, 위성 지도인 경우에는
                // GoogleMap.MAP_TYPE_SATELLITE
                googleMap.clear();
                markerOptions.position(new LatLng(outAddr.getLatitude(), outAddr.getLongitude()));
                markerOptions.title(location);
                markerOptions.snippet(sX + ", " + sY);
                markerOptions.draggable(true);

                Marker marker = googleMap.addMarker(markerOptions);
                marker.setDraggable(true);
                marker.showInfoWindow();
            }
        } catch (IOException ex) {
            Log.d("예외", "예외 : " + ex.toString());
        }
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        marker.hideInfoWindow();
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        marker.hideInfoWindow();
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        googleMap.clear();
        Double dx = marker.getPosition().latitude;
        Double dy = marker.getPosition().longitude;
        map.put("X",String.valueOf(dx));
        map.put("Y",String.valueOf(dy));

        markerOptions.position(new LatLng(dx, dy));
        markerOptions.title("지정된 곳의 좌표");
        markerOptions.snippet(dx + ", " + dy);
        markerOptions.draggable(true);

        marker = googleMap.addMarker(markerOptions);
        marker.setDraggable(true);
        marker.showInfoWindow();
    }

    @Override
    public void onMapClick(LatLng latLng) {
        googleMap.clear();
        Double tx = latLng.latitude;
        Double ty = latLng.longitude;
        map.put("X",String.valueOf(tx));
        map.put("Y",String.valueOf(ty));

        markerOptions.position(new LatLng(tx, ty));
        markerOptions.title("터치한 곳의 좌표");
        markerOptions.snippet(tx + ", " + ty);
        markerOptions.draggable(true);

        Marker marker = googleMap.addMarker(markerOptions);
        marker.setDraggable(true);
        marker.showInfoWindow();
    }
}
