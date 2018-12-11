package com.example.jypark.gazua;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

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
import java.util.Arrays;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    Button start, signUp, loginBtn, loginButton_fb;
    View dialogView;
    EditText nickname, nationality, detail, photo, x, y;
    String sEmail, sPassword, sNickname, sNationality, sDetail, sPhoto="basic", sX, sY;
    EditText email, password;
    TextView textView;
    HashMap<String,String> map = new HashMap<>();

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        start = findViewById(R.id.start);
        signUp = findViewById(R.id.signUp);

//START-------------------------------------------------------------------------------------------

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView = View.inflate(MainActivity.this, R.layout.activity_login, null);

                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("Sign In");
                dlg.setIcon(R.drawable.ic_menu_allfriends);
                dlg.setView(dialogView);

                loginBtn = dialogView.findViewById(R.id.LoginBtn);

                loginBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        email = dialogView.findViewById(R.id.Email);
                        password = dialogView.findViewById(R.id.Password);
                        textView = dialogView.findViewById(R.id.TextView);

                        String email_string = email.getText().toString();
                        String password_string = password.getText().toString();

                        String result = login(email_string, password_string);
                        String[][] parseData = jsonParserList(result); //json 데이터 파싱

                        try{
                            if(parseData[0][0].equals(email_string) & parseData[0][1].equals(password_string)){
                                Intent myIntent = new Intent(getApplicationContext(), CarouselActivity.class);     // AndroidManifast.xml 등록
                                startActivity(myIntent);
                                Toast.makeText(getApplicationContext(), "로그인 되었습니다.", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(getApplicationContext(), "아이디 혹은 비밀번호가 맞지 않습니다", Toast.LENGTH_LONG).show();
                            }
                        }catch (ArrayIndexOutOfBoundsException e){
                            Toast.makeText(getApplicationContext(), "아이디 혹은 비밀번호가 맞지 않습니다", Toast.LENGTH_LONG).show();
                        }



//                        String[] member = new String[parseData.length];
//
//                        for (int i = 0; i < member.length; i++) {
//                            member[i] = "이메일:" + parseData[i][0]
//                                    + "  비밀번호:" + parseData[i][1];
//
//                            textView.setText(member[i]);
//                        }
                    }
                });




            //뒤로가기버튼--------------------------------------------------------------------------
                        /*Toast toast=new Toast(MainActivity.this);
                        toastView = View.inflate(MainActivity.this, R.layout.toast1, null);
                        toast.setView(toastView);
                        toast.show();*/

                dlg.show();
            }
        });






//SIGNUP-------------------------------------------------------------------------------------------
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog1 = new SignUp();
                dialog1.show(getSupportFragmentManager(),"dialog1");
            }
        });

        // Facebook Login
        callbackManager = CallbackManager.Factory.create();

        loginButton_fb = findViewById(R.id.LoginButton_fb);
//        loginButton.setReadPermissions("email");

        Log.d("callbackManager", "callbackManager" + callbackManager);
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Log.d("success", "success" + loginResult);
            }

            @Override
            public void onCancel() {
                // App code
                Log.d("cancel", "onCancel");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.d("error", "onError");
            }
        });

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        Log.d("check", "check" + isLoggedIn);
        LoginManager.getInstance().logInWithReadPermissions(this,Arrays.asList("public_profile"));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    //서버에 데이터를 보내는 메서드
    public String login(String email, String password){
        URL url;
        String response = "";
        try {
            url = new URL("http://192.168.1.86:8088/Gazua/main.jsp");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("email", email)
                    .appendQueryParameter("password", password);
            String query = builder.build().getEncodedQuery();

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();

            int responseCode=conn.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
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

    //받은 JSON 객체를 파싱하는 메소드
    private String[][] jsonParserList(String pRecvServerPage) {

        Log.i("서버에서 받은 전체 내용 : ", pRecvServerPage);

        try {
            JSONObject json = new JSONObject(pRecvServerPage);
            JSONArray jArr = json.getJSONArray("list");

            // 받아온 pRecvServerPage를 분석하는 부분
            String[] jsonName = {"email", "password"};
            String[][] parseredData = new String[jArr.length()][jsonName.length];
            for (int i = 0; i < jArr.length(); i++) {
                json = jArr.getJSONObject(i);

                for (int j = 0; j < jsonName.length; j++) {
                    parseredData[i][j] = json.getString(jsonName[j]);
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

    @Override
    public void onBackPressed() {

    }
}
