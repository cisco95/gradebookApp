package com.example.gradebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class SignupActivity extends AppCompatActivity {
    private EditText emailId, password;
    private Button btnSignUp;
    private TextView tvSignIn;

    private ProgressDialog progressDialog;

    FirebaseAuth mainFirebaseAuth;
    DocumentReference mDocRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        progressDialog = new ProgressDialog(this);

        mainFirebaseAuth = FirebaseAuth.getInstance();


        emailId = findViewById(R.id.emailTxt);
        password = findViewById(R.id.pwdTxt);
        btnSignUp = findViewById(R.id.signUpBtn);
        tvSignIn = findViewById(R.id.SignInTxt);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                mDocRef = FirebaseFirestore.getInstance().document("Users/" + email);
                if (email.isEmpty()){
                    emailId.setError("Please enter an email");
                    emailId.requestFocus();
                }
                else if (pwd.isEmpty()){
                    password.setError("Please enter an email");
                    password.requestFocus();
                }
                else if (email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(SignupActivity.this, "Fields Are Empty!", Toast.LENGTH_SHORT).show();
                }
                else if (!(email.isEmpty() && pwd.isEmpty())){

                    progressDialog.setMessage("Registering User...");
                    progressDialog.show();

                    mainFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressDialog.dismiss();
                                Toast.makeText(SignupActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                                tvSignIn.setText("Log in here.");

                                Map<String, Object> dataToSave = new HashMap<String, Object>();
                                dataToSave.put("User Email", email);
                                dataToSave.put( "First Name", "John");
                                dataToSave.put("Last Name", "Doe");
                                dataToSave.put( "DOB", "04-27-2020");
                                mDocRef.set(dataToSave);

                                startActivity(new Intent (SignupActivity.this, HomeActivity.class));

                            }
                            else{
                                progressDialog.dismiss();
                                Toast.makeText(SignupActivity.this, "Sign Up Unsuccessful, Please Try Again", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
                else {
                    Toast.makeText(SignupActivity.this, "Error Occurred!", Toast.LENGTH_SHORT).show();
                }
            }

        });

        tvSignIn.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

    }

//    public void addUserToDB(View view){
//        emailId = findViewById(R.id.emailTxt);
//        String userEmail = emailId.getText().toString();
//        if (userEmail.isEmpty()) {
//            return;
//        }
//        Map<String, Object> dataToSave = new HashMap<String, Object>();
//        dataToSave.put("User Email", userEmail);
//        mDocRef.set(dataToSave);
//
//
//    }
}
