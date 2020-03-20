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
    private EditText Email;
    private EditText Password;
    private Button Login;
    private Button SignUp;
    FirebaseAuth myFireBaseAuth;
    private FirebaseAuth.AuthStateListener myAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.LoginPage); //<--This is important

        myFireBaseAuth = FirebaseAuth.getInstance();
        Email = findViewById(R.id.etEmail);
        Password = findViewById(R.id.etPassword);
        Login = findViewById(R.id.Login_btn);
        SignUp = findViewById(R.id.SignUp_btn);

        //myAuthStateListener = new FirebaseAuth.AuthStateListener(){@Override};

        SignUp.setOnClickListener(new View.OnClickListener() { //This code will tell the app what to do when user taps on the sign up button
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString(); //This will get the email from the text box
                String password = Password.getText().toString(); //This will get the password from the text box

                if(email.isEmpty()){ //If the email and/or password text box is empty when the user taps the sign up button then it will display an error message
                    Email.setError("Please type in an email");
                    Email.requestFocus();
                }
                else if(password.isEmpty()){
                    Password.setError("Please type in a password");
                    Password.requestFocus();
                }
                else if(email.isEmpty() && password.isEmpty()){ //When both text boxes are empty
                    Toast.makeText(LoginActivity.this, "Fields are empty", Toast.LENGTH_SHORT);
                }
                else if(!(email.isEmpty() && password.isEmpty())){ //
                    myFireBaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Sign up attempt was unsuccessful. Please try again.", Toast.LENGTH_SHORT);
                            }
                            else {
                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(LoginActivity.this, "An error has occurred!", Toast.LENGTH_SHORT);
                }
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
