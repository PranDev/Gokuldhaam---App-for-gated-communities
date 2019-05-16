package com.example.madlabproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.telephony.SmsManager;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;



public class Main7Activity extends AppCompatActivity {


    List<Status> statuses;
    ArrayList<String> stati = new ArrayList<String>();
    chetta thoo;
    CircleImageView profile_image;
    TextView username;
    FirebaseUser firebaseUser;
    static DatabaseReference reference;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        profile_image = findViewById(R.id.profile_image);
        username = (TextView) findViewById(R.id.usrnm);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        Button b1 = findViewById(R.id.button);
        Button b2 = findViewById(R.id.button2);
        EditText e2 = findViewById(R.id.editText2);
        b1.setVisibility(View.INVISIBLE);
        b2.setVisibility(View.INVISIBLE);
        e2.setVisibility(View.INVISIBLE);
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
        if (android.os.Build.VERSION.SDK_INT > 23) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        ListView k1 = findViewById(R.id.popsicle);
        String p ="";
        final Twitter twitter = getTwitterinstance();
        try{
            statuses = twitter.getHomeTimeline();
            thoo = new chetta(this, statuses);
            k1.setAdapter(thoo);
            final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh);

            //setting an setOnRefreshListener on the SwipeDownLayout
            pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                //Counting how many times user have refreshed the layout

                @Override
                public void onRefresh() {
                    try{
                        statuses = twitter.getHomeTimeline();
                        ListView k1 = findViewById(R.id.popsicle);
                        thoo = new chetta(Main7Activity.this, statuses);
                        k1.setAdapter(thoo);
                        pullToRefresh.setRefreshing(false);
                    }
                    catch(Exception e)
                    {
                        Toast.makeText(Main7Activity.this, "chee ni bathuku", Toast.LENGTH_SHORT);
                    }
                }
            });
        }
        catch(Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    public static Twitter getTwitterinstance()
    {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("0evYz57zNh8SQ6nmUHGsuLXJL")
                .setOAuthConsumerSecret("yABxOGlcMtV5bc2CGdZDQQ6SXAU8cybNfTcVGEgKcklskY2SLX")
                .setOAuthAccessToken("1115272400013103105-zfM7pdyUAVRtAxyDfcH78Ci5S5Fw6Q")
                .setOAuthAccessTokenSecret("pTR6Q4ThDmu0v5Be0FOsjybrjNFZLfNWWfNjuUMP6SxHB");

        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        return twitter;
    }


    //make a tweet
    public void meow (View v)
    {
        Twitter twitter = getTwitterinstance();
        EditText message= findViewById(R.id.editText2);
        String msg=message.getText().toString();
        try
        {
            Status status = twitter.updateStatus(msg);
            Toast.makeText(this, "Tweet succesfully made", Toast.LENGTH_SHORT);
            Log.d("chee","Successfully updated status to " + status.getText());
        }

        catch(Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //
    public void statustexts()
    {
        ListView k1 = findViewById(R.id.popsicle);
        Twitter twitter = getTwitterinstance();
        ArrayList<String> stati = new ArrayList<String>();
        try{
            List<Status> statuses = twitter.getHomeTimeline();
            for (Status status : statuses) {
                Date m=status.getCreatedAt();
                stati.add(status.getText());
            }
            String[] ppp = new String[stati.size()];
            for(int i =0;i<stati.size();i++)
            {
                ppp[i]=stati.get(i);
            }
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Chetta Nayala", Toast.LENGTH_SHORT).show();
        }
    }

    protected void arf(View v) {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        String phoneNo = "9908734345";
        EditText msg = findViewById(R.id.editText2);
        String message = msg.getText().toString();
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tweet,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {


            case R.id.Chat1:
            {
                Intent intent = new Intent(Main7Activity.this,Main4Activity.class);
                startActivity(intent);
                finish();
                return true;
            }

            case R.id.Logout1:
            {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(Main7Activity.this,MainActivity.class);
                startActivity(i);
                return true;
            }
            case R.id.ViewBookings1:
            {
                Intent i = new Intent(Main7Activity.this,Main11Activity.class);
                startActivity(i);
                return true;
            }

            case R.id.book1:
            {
                Intent i = new Intent(Main7Activity.this,Main9Activity.class);
                startActivity(i);
                return true;
            }

            case R.id.Home:
            {   if(firebaseUser.getUid().equals("zGpFOHrwMRZGchbOYvE4TQHCZ513"))
            {
                Intent i = new Intent(Main7Activity.this,Main5Activity.class);
                startActivity(i);
                return true;
            }
            else
            {
                Intent i = new Intent(Main7Activity.this,Main8Activity.class);
                startActivity(i);
                return true;
            }

            }
        }
        return super.onOptionsItemSelected(item);
    }

}

