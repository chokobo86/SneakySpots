package com.example.jypark.gazua;

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
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    Button start, signUp, loginBtn, loginButton_fb;
    View dialogView;
    EditText nickname, nationality, detail, photo, x, y;
    EditText email, password;
    TextView textView;
    HashMap<String,String> map = new HashMap<>();

//    com.google.android.gms.common.SignInButton google_login_button;
//    private static final int RC_SIGN_IN = 9001;
//    private FirebaseAuth mAuth;
//    private GoogleSignInClient mGoogleSignInClient;
//
//    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        start = findViewById(R.id.start);
        signUp = findViewById(R.id.signUp);
//        google_login_button = findViewById(R.id.google_login_button);

        // [START config_signin]
        // Configure Google Sign In
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
        // [END config_signin]

//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // [START initialize_auth]
//        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

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
//                        textView = dialogView.findViewById(R.id.TextView);

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
//        callbackManager = CallbackManager.Factory.create();
//
//        loginButton_fb = findViewById(R.id.LoginButton_fb);
////        loginButton.setReadPermissions("email");
//
//        Log.d("callbackManager", "callbackManager" + callbackManager);
//        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                // App code
//                Log.d("success", "success" + loginResult);
//            }
//
//            @Override
//            public void onCancel() {
//                // App code
//                Log.d("cancel", "onCancel");
//            }
//
//            @Override
//            public void onError(FacebookException exception) {
//                // App code
//                Log.d("error", "onError");
//            }
//        });
//
//        AccessToken accessToken = AccessToken.getCurrentAccessToken();
//        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
//        Log.d("check", "check" + isLoggedIn);
//        LoginManager.getInstance().logInWithReadPermissions(this,Arrays.asList("public_profile"));
//
//        google_login_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//                startActivityForResult(signInIntent, RC_SIGN_IN);
//            }
//        });
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        callbackManager.onActivityResult(requestCode, resultCode, data);
//        super.onActivityResult(requestCode, resultCode, data);
//        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                // Google Sign In was successful, authenticate with Firebase
//                GoogleSignInAccount account = task.getResult(ApiException.class);
//                firebaseAuthWithGoogle(account);
//            } catch (ApiException e) {
//                // Google Sign In failed, update UI appropriately
//                Log.w("Check Google sign : ", "Google sign in failed", e);
//                // [START_EXCLUDE]
//                updateUI(null);
//                // [END_EXCLUDE]
//            }
//        }
//    }

    // [START auth_with_google]
//    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
//        Log.d("check : ", "firebaseAuthWithGoogle:" + acct.getId());
//        // [START_EXCLUDE silent]
//        // [END_EXCLUDE]
//
//        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d("ck signInWithCredential", "signInWithCredential:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w("ck signInWithCredential", "signInWithCredential:failure", task.getException());
////                            Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
//                            updateUI(null);
//                        }
//
//                        // [START_EXCLUDE]
//                        // [END_EXCLUDE]
//                    }
//                });
//    }
    // [END auth_with_google]

//    private void updateUI(FirebaseUser user) {
//        if (user != null) {
//            mStatusTextView.setText(getString(R.string.google_status_fmt, user.getEmail()));
//            mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));
//
//            findViewById(R.id.signInButton).setVisibility(View.GONE);
//            findViewById(R.id.signOutAndDisconnect).setVisibility(View.VISIBLE);
//        } else {
//            mStatusTextView.setText(R.string.signed_out);
//            mDetailTextView.setText(null);
//
//            findViewById(R.id.signInButton).setVisibility(View.VISIBLE);
//            findViewById(R.id.signOutAndDisconnect).setVisibility(View.GONE);
//        }
//    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
//    }


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
}
