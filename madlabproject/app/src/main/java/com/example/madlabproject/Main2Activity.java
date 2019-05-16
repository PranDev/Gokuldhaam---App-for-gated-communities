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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Main2Activity extends AppCompatActivity {
    FirebaseAuth auth;
    EditText e1;
    EditText e2;
    EditText e3;
    Button b1;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         e1 = (EditText)findViewById(R.id.editText);
         e2 = (EditText)findViewById(R.id.editText2);
         e3 = (EditText)findViewById(R.id.editText3);
        b1 =(Button) findViewById(R.id.submit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("SIGN UP");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                if(s1.equals("") || s2.equals("") || s3.equals(""))
                {
                    Toast.makeText(Main2Activity.this,"All Fields Are Necessary",Toast.LENGTH_LONG).show();
                }
                else if(s3.length()<6){
                    Toast.makeText(Main2Activity.this,"Password length has to be greater than 6",Toast.LENGTH_LONG).show();
                }
                else {
                    register(s1, s2, s3);
                }
            }
        });


    }
    private void register(final String username, String emailid, String password)
    {
        auth.createUserWithEmailAndPassword(emailid,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser != null;
                            String userid = firebaseUser.getUid();
                            reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

                            HashMap<String,String> hashMap= new HashMap<>();
                            hashMap.put("id",userid);
                            hashMap.put("username",username);
                            hashMap.put("imageURL","default");
                            hashMap.put("status","offline");

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Intent i = new Intent(Main2Activity.this,MainActivity.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(i);
                                    finish();
                                }
                            });
                        }
                        else
                        {
                            Toast.makeText(Main2Activity.this,"Cant Add",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
