package com.example.madlabproject;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    FirebaseUser firebaseUser;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    public Context context;

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if(firebaseUser!=null) {
            String s1 = firebaseUser.getUid();
            if (s1.equals("zGpFOHrwMRZGchbOYvE4TQHCZ513")) {
                Intent i = new Intent(MainActivity.this, Main5Activity.class);
                startActivity(i);
                finish();
            } else {
                Intent i = new Intent(MainActivity.this, Main8Activity.class);
                startActivity(i);
                finish();
            }
        }
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer1);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(MainActivity.this);
        context=this;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if(firebaseUser!=null)
        {
            String s1 = firebaseUser.getUid();
            if (s1.equals("zGpFOHrwMRZGchbOYvE4TQHCZ513")) {
                Intent i = new Intent(MainActivity.this, Main5Activity.class);
                startActivity(i);
                finish();
            } else {
                Intent i = new Intent(MainActivity.this, Main8Activity.class);
                startActivity(i);
                finish();
            }
        }

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer1);
       actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
       drawerLayout.addDrawerListener(actionBarDrawerToggle);
       actionBarDrawerToggle.syncState();
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       NavigationView navigationView = findViewById(R.id.nav_view);
       navigationView.setNavigationItemSelectedListener(MainActivity.this);
       context=this;


    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        else{
            super.onBackPressed();
        }
    }

    public void SignIn(View view) {
        Intent i = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(i);
    }

    public void Login(View view) {
        Intent i = new Intent(MainActivity.this, Main3Activity.class);
        startActivity(i);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.men1:
                Intent i = new Intent(context,Main3Activity.class);
                startActivity(i);
            break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


