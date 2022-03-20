package com.nadiahassouni.noteswithfirebase;

import android.content.Context;
import android.util.Patterns;
import android.widget.Toast;

public class ValidateInput {
    Context context;
    ValidateInput(Context context){
        this.context = context;
    }

    boolean checkEmailIsValid(String email){
        if (email.length() == 0){
            Toast.makeText(context , "Please enter your email !", Toast.LENGTH_LONG).show();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(context , "Please enter a valid email !", Toast.LENGTH_LONG).show();
            return false;
        }else{
            return true;
        }
    }

    boolean checkPasswordIsValid(String password){
        if (password.length() == 0){
            Toast.makeText(context , "Please enter your password !", Toast.LENGTH_LONG).show();
            return false;
        } else if(password.length()<6){
            Toast.makeText(context , "Please enter a password of at least 6 characters !", Toast.LENGTH_LONG).show();
            return false;
        }else{
            return true;
        }
    }
}
