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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class SignupActivity extends AppCompatActivity {
    private EditText emailId, password, firstName, lastName, DOB;
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
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        DOB = findViewById(R.id.DOB);

        btnSignUp = findViewById(R.id.signUpBtn);
        tvSignIn = findViewById(R.id.SignInTxt);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                final String fName = firstName.getText().toString();
                final String lName = lastName.getText().toString();
                final String bDay = DOB.getText().toString();
                String mFirebaseUser = mainFirebaseAuth.getUid();

                mDocRef = FirebaseFirestore.getInstance().document("Users/" + mFirebaseUser);
                if (email.isEmpty()){
                    emailId.setError("Please enter an email");
                    emailId.requestFocus();
                }
                else if (pwd.isEmpty()){
                    password.setError("Please enter a password");
                    password.requestFocus();
                }
                else if (fName.isEmpty()){
                    firstName.setError("Please enter a First Name");
                    firstName.requestFocus();
                }
                else if (lName.isEmpty()){
                    lastName.setError("Please enter a Last Name");
                    lastName.requestFocus();
                }
                else if (bDay.isEmpty()){
                    DOB.setError("Please enter a Date of Birth");
                    DOB.requestFocus();
                }

                else if (email.isEmpty() && pwd.isEmpty() && fName.isEmpty() && lName.isEmpty() && bDay.isEmpty()){
                    Toast.makeText(SignupActivity.this, "All Fields Are Empty!", Toast.LENGTH_SHORT).show();
                }
                else if (!(email.isEmpty() && pwd.isEmpty() && fName.isEmpty() && lName.isEmpty() && bDay.isEmpty())){

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
                                dataToSave.put( "First Name", fName);
                                dataToSave.put("Last Name", lName);
                                dataToSave.put( "DOB", bDay);
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
