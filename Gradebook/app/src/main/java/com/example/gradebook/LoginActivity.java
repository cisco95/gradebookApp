package com.example.gradebook;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends Activity {
    private EditText emailId;
    private EditText password;
    private Button btnSignIn;
    private Button tvSignUp;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener myAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); //<--This is important

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        btnSignIn = findViewById(R.id.Login_btn);
        tvSignUp = findViewById(R.id.SignUp_btn);

        myAuthStateListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if(mFirebaseUser != null){
                    Toast.makeText(LoginActivity.this, "You are Logged In" , Toast.LENGTH_LONG).show();
                    Intent i = new Intent (LoginActivity.this, HomeActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Please Log In", Toast.LENGTH_SHORT).show();

                }
            }
        };

        btnSignIn.setOnClickListener(new View.OnClickListener() { //This code will tell the app what to do when user taps on the sign up button
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString(); //This will get the email from the text box
                String pwd = password.getText().toString(); //This will get the password from the text box

                if(email.isEmpty()){ //If the email and/or password text box is empty when the user taps the sign up button then it will display an error message
                    emailId.setError("Please enter an Email");
                    emailId.requestFocus();
                }
                else if(pwd.isEmpty()){
                    password.setError("Please enter your Password");
                    password.requestFocus();
                }
                else if(email.isEmpty() && pwd.isEmpty()){ //When both text boxes are empty
                    Toast.makeText(LoginActivity.this, "Fields are empty", Toast.LENGTH_SHORT);
                    emailId.setError("Please enter an Email");
                    password.setError("Please enter your Password");
                }
                else if(!(email.isEmpty() && pwd.isEmpty())){ //
                    mFirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!(task.isSuccessful())){
                                Toast.makeText(LoginActivity.this, "Login Unsuccessful, Please Sign Up", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Intent intToHome = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intToHome);
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(LoginActivity.this, "An error has occurred!", Toast.LENGTH_SHORT);

                }
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignUp = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intSignUp);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(myAuthStateListener);
    }
}
