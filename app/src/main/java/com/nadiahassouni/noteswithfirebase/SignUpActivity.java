package com.nadiahassouni.noteswithfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nadiahassouni.noteswithfirebase.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding binding;
    private ValidateInput validateInput;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        validateInput = new ValidateInput(this);
        firebaseAuth = FirebaseAuth.getInstance();

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSignUp();
            }
        });

        binding.tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignInActivity();
            }
        });
    }

    private void handleSignUp() {
        String email = binding.etEmail.getText().toString();
        String password1 = binding.et1Password.getText().toString();
        String password2 = binding.et2Password.getText().toString();
        if (validateInput.checkEmailIsValid(email) && validateInput.checkPasswordIsValid(password1)){
            if(password1.equals(password2)){
                createUser(email , password1);
            }else {
                Toast.makeText(this , "Passwords don't match, please enter again !" , Toast.LENGTH_LONG).show();
            }
        }
    }

    private void createUser(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(SignUpActivity.this , "Sign Up is successful ! " , Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(SignUpActivity.this , "Error occurred : " + task.getException() , Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    private void openSignInActivity() {
        startActivity( new Intent(SignUpActivity.this , LogInActivity.class));
    }
}