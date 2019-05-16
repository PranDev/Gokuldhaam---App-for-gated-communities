package com.example.madlabproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.madlabproject.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Main11Activity extends AppCompatActivity {
    ListView lv;
    ArrayList<contact> contactList;
    CustomAdapter clAdapter;
    CircleImageView profile_image;
    TextView username;
    FirebaseUser firebaseUser;
    static DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        profile_image = findViewById(R.id.profile_image);
        username = (TextView) findViewById(R.id.usrnm);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                username.setText(user.getUsername());
                if(user.getImageURL().equals("default"))
                {
                    profile_image.setImageResource(R.mipmap.ic_launcher);

                }
                else
                {
                    Glide.with(getApplicationContext()).load(user.getImageURL()).into(profile_image);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        lv = (ListView) findViewById(R.id.listView1);
        disp();
    }

    private void disp() {
        SQLiteDatabase mydatabase = openOrCreateDatabase("bookdb",MODE_PRIVATE,null);
        Cursor resultset = mydatabase.rawQuery("SELECT * FROM CONTACTS",null);
        resultset.moveToFirst();
        int i;
        contactList = new ArrayList<contact>();
        for(i=0;i<resultset.getCount();i++)
        {
            contactList.add(new contact("BID:"+resultset.getString(0),"Service:"+resultset.getString(1),resultset.getInt(2)));
            resultset.moveToNext();

        }
        clAdapter = new CustomAdapter(contactList,this);
        lv.setAdapter(clAdapter);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.tweet:
            {
                if(firebaseUser.getUid().equals("zGpFOHrwMRZGchbOYvE4TQHCZ513"))
                {
                    Intent i = new Intent(Main11Activity.this,Main6Activity.class);
                    startActivity(i);
                    return true;
                }
                else{
                    Intent i = new Intent(Main11Activity.this,Main7Activity.class);
                    startActivity(i);
                    return true;
                }
            }

            case R.id.Chat1:
            {
                Intent intent = new Intent(Main11Activity.this,Main4Activity.class);
                startActivity(intent);
                finish();
                return true;
            }

            case R.id.Logout1:
            {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(Main11Activity.this,MainActivity.class);
                startActivity(i);
                return true;
            }
            case R.id.book1:
            {
                Intent i = new Intent(Main11Activity.this,Main9Activity.class);
                startActivity(i);
                return true;
            }

            case R.id.home:
            {
                if(firebaseUser.getUid().equals("zGpFOHrwMRZGchbOYvE4TQHCZ513"))
                {
                    Intent i = new Intent(Main11Activity.this,Main5Activity.class);
                    startActivity(i);
                    return true;
                }
                else{
                    Intent i = new Intent(Main11Activity.this,Main8Activity.class);
                    startActivity(i);
                    return true;
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
