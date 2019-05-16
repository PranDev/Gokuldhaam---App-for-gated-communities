package com.example.madlabproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Main3Activity extends AppCompatActivity {
    FirebaseAuth auth;
    EditText e1;
    EditText e2;
    Button b1;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        auth = FirebaseAuth.getInstance();
        e1=(EditText) findViewById(R.id.editText4);
        e2=(EditText) findViewById(R.id.editText5);
        b1=findViewById(R.id.Login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("LOGIN");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();

                auth.signInWithEmailAndPassword(s1,s2)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    if(s1.equals("manager@gmail.com"))
                                    {
                                        Toast.makeText(Main3Activity.this, "Login Successful", Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(Main3Activity.this, Main5Activity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(Main3Activity.this, "Login Successful", Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(Main3Activity.this, Main8Activity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                }
                                else{
                                    Toast.makeText(Main3Activity.this,"Please Enter Valid Details",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }
}
