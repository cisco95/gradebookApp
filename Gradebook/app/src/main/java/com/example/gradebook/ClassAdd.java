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

//import com.google.firebase.database.FirebaseDatabase;

public class ClassAdd extends AppCompatActivity {

    private EditText className;
    private Button btn_addClass, btn_cancel;

    private ProgressDialog progressDialog;

//    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_add);

        className = findViewById(R.id.className);
        btn_addClass = findViewById(R.id.btn_addClass);
        btn_cancel = findViewById(R.id.btn_cancel);
        progressDialog = new ProgressDialog(this);

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
                Toast.makeText(ClassAdd.this, "Feature Coming Soon", Toast.LENGTH_LONG).show();
            }
        });
    }
}
