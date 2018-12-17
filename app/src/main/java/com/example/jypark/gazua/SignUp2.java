package com.example.jypark.gazua;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class SignUp2 extends DialogFragment {
    String sNickname, sNationality, sDetail, sPhoto="basic";
    String[] item;
    Spinner spinner;
    EditText nickname, detail;
    TextView nationality;
    HashMap<String,String> map = null;

//==================================================== Photo ================================================================
    private static final int MY_PERMISSION_CAMERA = 1111;
    private static final int REQUEST_TAKE_PHOTO = 2222;
    private static final int REQUEST_TAKE_ALBUM = 3333;
    private static final int REQUEST_IMAGE_CROP = 4444;

    ImageView photo;

    String mCurrentPhotoPath;

    Uri imageUri;
    Uri photoURI, albumURI;



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_signup2, null);
        dlg.setTitle("SIGN UP");
        dlg.setIcon(R.drawable.ic_menu_allfriends);

//        getActivity().setContentView(R.layout.activity_signup2);

        photo = view.findViewById(R.id.Photo);

        if(Build.VERSION.SDK_INT >= 21) {
            photo.setClipToOutline(true);
        }

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence[] options = {"카메라로 촬영", "앨범에서 사진 찾기"};

                checkPermission();
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("사진 가져오는 방법 선택")
                        .setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(which == 0){
                                    captureCamera();
                                }else{
                                    getAlbum();
                                }
                            }
                        })
                        .show();
            }
        });



//==================================================== Nickname & Detail=====================================================

        nickname = view.findViewById(R.id.Nickname);
        detail = view.findViewById(R.id.Detail);

//==================================================== Nationality ==========================================================

        spinner = view.findViewById(R.id.Nationality);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("", ""+position);
                Log.d("", ""+parent.getItemAtPosition(position));
                parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        item = new String[Locale.getISOCountries().length];
        for( int i=0; i < item.length; i++ ){
            item[i] = new Locale("en", Locale.getISOCountries()[i]).getDisplayCountry();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);



//==================================================== Sign Up 3 으로 보내기 ===================================================
        dlg.setView(view)
                .setPositiveButton("다음", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d("imageUri11", ""+imageUri);
                        Log.d("albumURI11", ""+albumURI);

                        if(imageUri == null){
                            sPhoto = albumURI.toString();
                        }else{
                            sPhoto = imageUri.toString();
                        }

                        sNickname = nickname.getText().toString();

                        sNationality = spinner.getSelectedItem().toString();
                        Log.d("123",""+sNationality);
                        sDetail = detail.getText().toString();


                        map.put("photo",sPhoto);
                        map.put("nickname",sNickname);
                        map.put("nationality",sNationality);
                        map.put("detail",sDetail);


                        DialogFragment dialog3 = new SignUp3();
                        ((SignUp3) dialog3).getHashMap(map);
                        dialog3.show(getFragmentManager(),"dialog3");
                    }
                })
                .setNegativeButton("이전", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DialogFragment dialog1 = new SignUp();
                        ((SignUp) dialog1).getHashMap(map);
                        dialog1.show(getFragmentManager(),"dialog1");
                    }
                });

        return dlg.create();
    }


//==================================================== Photo2 ================================================================


    private void captureCamera(){
        String state = Environment.getExternalStorageState();
        // 외장 메모리 검사
        if(Environment.MEDIA_MOUNTED.equals(state)){
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            if(takePictureIntent.resolveActivity(getContext().getPackageManager()) != null){
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                }catch(IOException ex){
                    Log.e("captureCamera Error", ex.toString());
                }
                if(photoFile != null){
                    //getUriForFile의 두 번째 인자는 Manifest provider의 authorites와 일치해야 함
                    Log.d("",""+photoFile);
                    Log.d("",""+getContext());
                    Log.d("",""+getContext().getPackageName());
                    Uri providerURI = FileProvider.getUriForFile(getContext(), getContext().getPackageName(), photoFile);

                    imageUri = providerURI;
                    Log.e("imageUri", ""+imageUri);
                    //인텐트에 전달할 때는 FileProvider의 Return값인 content://로만!!, providerURI의 값에 카메라 데이터를 넣어 보냄
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, providerURI);

                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                    Log.e("takePictureIntent", ""+takePictureIntent);
                }
            }
        }else{
            Toast.makeText(getContext(), "저장공간이 접근 불가능한 기기입니다.", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public File createImageFile() throws IOException {
        //Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + ".jpg";
        File imageFile = null;
        File storageDir = new File(Environment.getExternalStorageDirectory() + "/Pictures","Indigo");
        Log.i("storageDir", storageDir.toString());
        if(!storageDir.exists()){
            Log.i("mCurrentPhotoPath1", storageDir.toString());
            storageDir.mkdir();
        }

        imageFile = new File(storageDir, imageFileName);
        mCurrentPhotoPath = imageFile.getAbsolutePath();
        Log.i("imageFile", ""+imageFile);
        Log.i("mCurrentPhotoPath", mCurrentPhotoPath);
        return imageFile;
    }

    private void getAlbum(){
        Log.i("getAlbum", "Call");
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, REQUEST_TAKE_ALBUM);
    }


    private void galleryAddPic(){
        Log.i("galleryAddPic", "Call");
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        //해당 경로에 있는 파일을 객체화 (새로 파일을 만든다는 것으로 이해하면 안 됨)
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        getContext().sendBroadcast(mediaScanIntent);
        Toast.makeText(getContext(), "사진이 앨범에 저장되었습니다.", Toast.LENGTH_SHORT).show();
    }

    //카메라 전용 크롭
    public void cropImage(){
        Log.i("cropImage", "Call");
        Log.i("cropImage", "photoURI : " + photoURI + " / albumURI " + albumURI);

        Intent cropIntent = new Intent("com.android.camera.action.CROP");

        // 50x50픽셀미만은 편집할 수 없다는 문구 처리 + 갤러리, 포토 둘다 호환하는 방법
        cropIntent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        cropIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        cropIntent.setDataAndType(photoURI, "image/*");
        //cropIntent.putExtra("outputX", 200); crop한 이미지의 x축 크기, 결과물의 크기
        //cropIntent.putExtra("outputY", 200); crop한 이미지의 y축 크기
        cropIntent.putExtra("aspectX", 1); //crop 박스의 x축 비율, 1x1이면 정사각형
        cropIntent.putExtra("aspectY", 1); //crop 박스의 y축 비율
        cropIntent.putExtra("scale", true);
        cropIntent.putExtra("output", albumURI); //크롭된 이미지를 해당 경로에 저장
        Log.i("cropImage", "Call2");
        startActivityForResult(cropIntent, REQUEST_IMAGE_CROP);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case REQUEST_TAKE_PHOTO:
                if(resultCode == Activity.RESULT_OK){
                    try{
                        Log.i("REQUEST_TAKE_PHOTO", "OK");
                        galleryAddPic();

                        photo.setImageURI(imageUri);
                        Log.d("",""+imageUri);

                        photo.setBackground(new ShapeDrawable(new OvalShape()));
                        if(Build.VERSION.SDK_INT >= 21) {
                            photo.setClipToOutline(true);
                        }
                    }catch (Exception e){
                        Log.e("REQUEST_TAKE_PHOTO", e.toString());
                    }
                }else {
                    Toast.makeText(getContext(), "사진찍기를 취소하였습니다.", Toast.LENGTH_SHORT).show();
                }
                break;

            case REQUEST_TAKE_ALBUM:
                if(resultCode == Activity.RESULT_OK){
                    if(data.getData() != null){
                        try{
                            File albumFile = null;
                            albumFile = createImageFile();
                            photoURI = data.getData();
                            albumURI = Uri.fromFile(albumFile);
                            Log.i("albumURI", ""+albumURI);
                            cropImage();
                        }catch (Exception e){
                            Log.e("TAKE_ALBUM_SINGLE ERROR", e.toString());

                        }
                    }
                }
                break;

            case REQUEST_IMAGE_CROP:
                if(resultCode == Activity.RESULT_OK){
                    Log.i("resultCode", ""+resultCode);
                    galleryAddPic();
                    photo.setImageURI(albumURI);

                    photo.setBackground(new ShapeDrawable(new OvalShape()));
                    if(Build.VERSION.SDK_INT >= 21) {
                        photo.setClipToOutline(true);
                    }
                }
                break;
        }
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions((Activity)mContext., new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSION_CAMERA);


            if ((ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) ||
                    (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA))) {
                new android.app.AlertDialog.Builder(getContext())
                        .setTitle("알림")
                        .setMessage("저장소 권한이 거부되었습니다. 사용을 원하시면 설정에서 해당 권한을 직접 허용하셔야 합니다.")
                        .setNeutralButton("설정", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.setData(Uri.parse("package:" + getActivity().getPackageName()));
                                startActivity(intent);
                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getActivity().finish();
                            }
                        })
                        .setCancelable(false)
                        .create()
                        .show();
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, MY_PERMISSION_CAMERA);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case MY_PERMISSION_CAMERA:
                for(int i = 0; i < grantResults.length; i++){
                    // grantResults[] : 허용된 권한은 0, 거부한 권한은 -1
                    if(grantResults[i] < 0){
                        Toast.makeText(getContext(), "해당 권한을 활성화 하셔야 합니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                break;
        }
    }

    public void getHashMap(HashMap<String,String> map){
        this.map = map;
    }
}