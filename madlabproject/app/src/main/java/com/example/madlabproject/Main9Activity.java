package com.example.madlabproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
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

public class Main9Activity extends AppCompatActivity {

    static int price =0;
    ArrayList<String> s1 = new ArrayList<>();
    CircleImageView profile_image;
    TextView username;
    FirebaseUser firebaseUser;
    static DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

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
    }

    public void onCheckBoxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBox:
                if (checked)
                {
                  price=price+300;
                }
                // Put some meat on the sandwich
            else
                {
                    price=price-300;
                }
                // Remove the meat
                break;
            case R.id.checkBox2:
                if (checked)
                {
                    price=price+300;
                }
                // Cheese me
            else
                {
                    price=price-300;
                }
                // I'm lactose intolerant
                break;
            case R.id.checkBox3:
                if (checked)
                {
                    price=price-300;
                }
                // Cheese me
            else
                {

                }
                // I'm lactose intolerant
                break;
            case R.id.checkBox4:
                if (checked)
                {
                    price=price+300;
                }
                // Cheese me
            else
                {
                    price=price-300;
                }
                // I'm lactose intolerant
                break;
        }
    }

    public void Book(View view) {
        s1.clear();
        CheckBox c1 = findViewById(R.id.checkBox);
        CheckBox c2 = findViewById(R.id.checkBox2);
        CheckBox c3 = findViewById(R.id.checkBox3);
        CheckBox c4 = findViewById(R.id.checkBox4);

        if(c1.isChecked())
        {
            s1.add(c1.getText().toString());
        }

        if(c2.isChecked())
        {
            s1.add(c2.getText().toString());
        }

        if(c3.isChecked())
        {
            s1.add(c3.getText().toString());
        }
        if(c4.isChecked())
        {
            s1.add(c4.getText().toString());
        }
        Bundle bundle = new Bundle();
        bundle.putInt("cost",price);
        bundle.putStringArrayList("Names",s1);
        Intent intent = new Intent(Main9Activity.this,Main10Activity.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_book,menu);
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
                    Intent i = new Intent(Main9Activity.this,Main6Activity.class);
                    startActivity(i);
                    return true;
                }
                else{
                    Intent i = new Intent(Main9Activity.this,Main7Activity.class);
                    startActivity(i);
                    return true;
                }
            }

            case R.id.Chat1:
            {
                Intent intent = new Intent(Main9Activity.this,Main4Activity.class);
                startActivity(intent);
                finish();
                return true;
            }

            case R.id.Logout1:
            {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(Main9Activity.this,MainActivity.class);
                startActivity(i);
                return true;
            }
            case R.id.ViewBookings:
            {
                Intent i = new Intent(Main9Activity.this,Main11Activity.class);
                startActivity(i);
                return true;
            }

            case R.id.Home:
            {
                if(firebaseUser.getUid().equals("zGpFOHrwMRZGchbOYvE4TQHCZ513"))
                {
                    Intent i = new Intent(Main9Activity.this,Main5Activity.class);
                    startActivity(i);
                    return true;
                }
                else{
                    Intent i = new Intent(Main9Activity.this,Main8Activity.class);
                    startActivity(i);
                    return true;
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
