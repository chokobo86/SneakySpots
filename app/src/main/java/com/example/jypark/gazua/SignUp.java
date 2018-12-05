package com.example.jypark.gazua;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;

public class SignUp extends DialogFragment {
    String sEmail, sPassword;
    EditText EMAIL, PASSWORD;
    HashMap<String,String> map = null;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_signup, null);

        EMAIL = view.findViewById(R.id.EMAIL);
        PASSWORD = view.findViewById(R.id.PASSWORD);

        dlg.setTitle("SIGN UP");
        dlg.setIcon(R.drawable.ic_menu_allfriends);

        dlg.setView(view)
                .setPositiveButton("다음", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        sEmail = EMAIL.getText().toString();
                        sPassword = PASSWORD.getText().toString();

                        HashMap<String,String> map = new HashMap<>();
                        map.put("email",sEmail);
                        map.put("password",sPassword);

                        DialogFragment dialog2 = new SignUp2();
                        ((SignUp2) dialog2).getHashMap(map);
                        dialog2.show(getFragmentManager(),"dialog2");
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return dlg.create();
    }

    public void getHashMap(HashMap<String,String> map){
        this.map = map;
    }
}