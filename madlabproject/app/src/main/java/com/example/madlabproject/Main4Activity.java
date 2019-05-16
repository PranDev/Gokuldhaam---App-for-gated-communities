package com.example.madlabproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.madlabproject.Fragments.ChatsFragment;
import com.example.madlabproject.Fragments.ProfileFragment;
import com.example.madlabproject.Fragments.UsersFragment;
import com.example.madlabproject.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class Main4Activity extends AppCompatActivity {
    CircleImageView profile_image;
    TextView username;
    FirebaseUser firebaseUser;
    static DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
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

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.view_pager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragement(new ChatsFragment(),"Chats");
        viewPagerAdapter.addFragement(new UsersFragment(),"Users");
        viewPagerAdapter.addFragement(new ProfileFragment(),"Profile");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.logout:
            {FirebaseAuth.getInstance().signOut();
            Intent i = new Intent(Main4Activity.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            return true;}
            case R.id.TWEET:
            {
                if(firebaseUser.getUid().equals("zGpFOHrwMRZGchbOYvE4TQHCZ513"))
                {
                    Intent i = new Intent(Main4Activity.this,Main6Activity.class);
                    startActivity(i);
                    return true;
                }
                else{
                    Intent i = new Intent(Main4Activity.this,Main7Activity.class);
                    startActivity(i);
                    return true;
                }
            }

            case R.id.Chat1:
            {
                Intent intent = new Intent(Main4Activity.this,Main4Activity.class);
                startActivity(intent);
                finish();
                return true;
            }

            case R.id.book:
            {
                Intent i = new Intent(Main4Activity.this,Main9Activity.class);
                startActivity(i);
                return true;
            }

            case R.id.Home:
            {
                if(firebaseUser.getUid().equals("zGpFOHrwMRZGchbOYvE4TQHCZ513"))
                {
                    Intent i = new Intent(Main4Activity.this,Main5Activity.class);
                    startActivity(i);
                    return true;
                }
                else{
                    Intent i = new Intent(Main4Activity.this,Main8Activity.class);
                    startActivity(i);
                    return true;
                }
            }

        }
        return super.onOptionsItemSelected(item);
    }


class ViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;
    ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragments=new ArrayList<>();
        this.titles=new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void addFragement(Fragment fragment, String title)
    {
        fragments.add(fragment);
        titles.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        return titles.get(position);
    }


}

    private void status(String status)
    {
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("status",status);

        reference.updateChildren(hashMap);

    }

    @Override
    protected void onResume() {
        super.onResume();
        status("online");
    }

    @Override
    protected void onPause() {
        super.onPause();
        status("offline");
    }
}