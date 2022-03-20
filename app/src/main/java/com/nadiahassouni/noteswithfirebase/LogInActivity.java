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
import com.nadiahassouni.noteswithfirebase.databinding.ActivityLogInBinding;

public class LogInActivity extends AppCompatActivity {
    private ActivityLogInBinding binding;
    private ValidateInput validateInput;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogInBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        validateInput = new ValidateInput(this);
        firebaseAuth = FirebaseAuth.getInstance();

        binding.btnSignIn.setOnClickListener(view12 -> handleLogin());

        binding.tvSignUp.setOnClickListener(view1 -> openSignUpActivity());
    }

    private void handleLogin() {
        String email = binding.etEmail.getText().toString();
        String password = binding.etPassword.getText().toString();
        if(validateInput.checkEmailIsValid(email) && validateInput.checkPasswordIsValid(password)){
            signIn(email , password);
        }
    }

    private void signIn(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email , password).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                startActivity(new Intent(LogInActivity.this , NotesListActivity.class));
            }else{
                Toast.makeText(LogInActivity.this , "error occurred " + task.getException() , Toast.LENGTH_LONG).show();

            }
        });
    }

    private void openSignUpActivity() {
        startActivity( new Intent(LogInActivity.this , SignUpActivity.class));

    }
}