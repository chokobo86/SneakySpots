package com.example.jypark.gazua;


import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.app.AlertDialog;

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
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

public class RanksActivity extends AppCompatActivity{
    View dialogView;
    ListView listView;
    int numUser = 0, numParam = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranks);

        ArrayList<String> list = new ArrayList<>();
        String [][] userList = jsonParserList(RankList());

        for(String[] i : userList){
            list.add("닉네임 : " + i[0] + " 포인트 : " + i[3]);
        }

        listView = findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

    }

    public String RankList(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL url;
        String response = "";

        try {
            url = new URL("http://192.168.1.86:8088/Gazua/RankList.jsp");
            Log.i("url : ","http://192.168.1.86:8088/Gazua/insertUser.jsp");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            String query = String.valueOf(url);
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
        Log.d("JSON : ", response);
        return response;
    }

    private String[][] jsonParserList(String pRecvServerPage) {

        Log.i("서버에서 받은 전체 내용 : ", pRecvServerPage);

        try {
            JSONObject json = new JSONObject(pRecvServerPage);
            JSONArray jArr = json.getJSONArray("list");

            // 받아온 pRecvServerPage를 분석하는 부분
            String[] jsonName = {"nickname", "point_distance", "point_rarity", "point_total",
                    "achievement", "achievement_done", "collection", "loc_x", "loc_y"};
            String[][] parseredData = new String[jArr.length()][jsonName.length];
            numUser = jArr.length();
            numParam = jsonName.length;
            for (int i = 0; i < jArr.length(); i++) {
                json = jArr.getJSONObject(i);

                for (int j = 0; j < jsonName.length; j++) {
                    parseredData[i][j] = json.getString(jsonName[j]);
                }
            }

            // 분해 된 데이터를 확인하기 위한 부분
            for (int i = 0; i < parseredData.length; i++) {
                Log.i("JSON을 분석한 데이터 " + i + " : ", parseredData[i][0]);
                Log.i("JSON을 분석한 데이터 " + i + " : ", parseredData[i][4]);
            }

            return parseredData;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}