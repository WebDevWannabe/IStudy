package com.app.jl.istudy.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.jl.istudy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonSignUp;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewLogIn;

    private ProgressDialog progressDialogSignUp;
    private ProgressDialog progressDialogEmailVerification;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();

        buttonSignUp = (Button)findViewById(R.id.buttonSignUp);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        textViewLogIn = (TextView)findViewById(R.id.textViewLogIn);

        progressDialogSignUp = new ProgressDialog(this);
        progressDialogEmailVerification = new ProgressDialog(this);

        buttonSignUp.setOnClickListener(this);
        textViewLogIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonSignUp) {
            //sign up the user then back to log in activity
            signUp();
        }
        else if(v == textViewLogIn) {
            //back to log in activity
            logIn();
        }
    }

    private void signUp() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Enter your desired email", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Enter your desired password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialogSignUp.setMessage("Signing Up...");
        progressDialogSignUp.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "Signed Up Successfully", Toast.LENGTH_SHORT).show();
                    editTextEmail.setText("");
                    editTextPassword.setText("");
                    emailVerification();
                }
                else {
                    Toast.makeText(SignUpActivity.this, "Sign Up failed", Toast.LENGTH_SHORT).show();
                    editTextPassword.setText("");
                }
                progressDialogSignUp.dismiss();
            }
        });
    }

    private void logIn() {
        editTextEmail.setText("");
        editTextPassword.setText("");
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void emailVerification() {
        progressDialogEmailVerification.setMessage("Sending email verification...");
        progressDialogEmailVerification.show();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        firebaseUser.sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "Verification email sent", Toast.LENGTH_SHORT).show();
                    firebaseAuth.signOut();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(SignUpActivity.this, "Sending verification email failed", Toast.LENGTH_SHORT).show();
                    editTextPassword.setText("");
                }
                progressDialogEmailVerification.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
