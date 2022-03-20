package com.nadiahassouni.noteswithfirebase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class NotesListActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list_activity);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void showLogoutDialog(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setMessage("Are you sure you want to logout ?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        firebaseAuth.signOut();
                        ((Activity) context).finish();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }) ;
        AlertDialog logoutAlertDialog = builder.create();
        logoutAlertDialog.show();

    }
}