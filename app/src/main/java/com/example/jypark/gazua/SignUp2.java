package com.example.jypark.gazua;

import android.app.Dialog;
import android.content.DialogInterface;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;

public class SignUp2 extends DialogFragment {
    String sNickname, sNationality, sDetail, sPhoto="basic";
    EditText NICKNAME, NATIONALITY, DETAIL, PHOTO;
    HashMap<String,String> map = null;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_signup2, null);

        NICKNAME = view.findViewById(R.id.NICKNAME);
        NATIONALITY = view.findViewById(R.id.NATIONALITY);
        DETAIL = view.findViewById(R.id.DETAIL);
        PHOTO = view.findViewById(R.id.PHOTO);

        dlg.setTitle("SIGN UP");
        dlg.setIcon(R.drawable.ic_menu_allfriends);

        dlg.setView(view)
                .setPositiveButton("다음", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        sNickname = NICKNAME.getText().toString();
                        sNationality = NATIONALITY.getText().toString();
                        sDetail = DETAIL.getText().toString();
                        sPhoto = PHOTO.getText().toString();

                        map.put("nickname",sNickname);
                        map.put("nationality",sNationality);
                        map.put("detail",sDetail);
                        map.put("photo",sPhoto);

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

    public void getHashMap(HashMap<String,String> map){
        this.map = map;
    }
}