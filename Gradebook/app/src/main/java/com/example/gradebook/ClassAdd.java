package com.example.gradebook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ClassAdd extends AppCompatActivity {


    private EditText className;
    private Button btn_addClass, btn_cancel;

    private ProgressDialog progressDialog;

    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_add);

        mFirebaseAuth = FirebaseAuth.getInstance();
        className = findViewById(R.id.className);
        btn_addClass = findViewById(R.id.btn_addClass);
        btn_cancel = findViewById(R.id.btn_cancel);
        progressDialog = new ProgressDialog(this);
        db = FirebaseFirestore.getInstance();



        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ClassAdd.this, HomeActivity.class);
                startActivity(i);
            }
        });

        btn_addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String mFirebaseUser = mFirebaseAuth.getUid();
                String classTitle = className.getText().toString();
                CourseList courseList = new CourseList();
                Course course = new Course(classTitle);
                courseList.addSemesterCourse(course);

//                if(mFirebaseUser != null){
                    //add class
//                    Map<String, Object> dataToSave = new HashMap<String, Object>();
//                    dataToSave.put("Class Title", className);
//                    dataToSave.put("Class Grade", 100);
//
//                    db.collection("Users").document(mFirebaseUser)
//                                    .collection("Class").add(dataToSave);
//
                    Intent i = new Intent( ClassAdd.this, MainActivity.class);
                    startActivity(i);

                }
//                else {
//                    Toast.makeText(ClassAdd.this, "Please Log In", Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent (ClassAdd.this, LoginActivity.class);
//                    startActivity(i);

                }
                //Toast.makeText(ClassAdd.this, "Feature Coming Soon", Toast.LENGTH_LONG).show();
            }
        });
    }
}
