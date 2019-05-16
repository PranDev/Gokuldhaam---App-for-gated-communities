package com.example.madlabproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import twitter4j.Status;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class chetta extends ArrayAdapter {

    private final Activity context;
    private List<Status> statusArray;

    public chetta(Activity context, List<Status> statusArrayParam){
        super(context,R.layout.chetta, statusArrayParam);
        this.context=context;
        this.statusArray = statusArrayParam;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View chettaView=inflater.inflate(R.layout.chetta, null,true);
        TextView t1 = (TextView) chettaView.findViewById(R.id.textView);
        TextView t2 = (TextView) chettaView.findViewById(R.id.textView2);
        t1.setText(statusArray.get(position).getText());
        t2.setText(statusArray.get(position).getCreatedAt().toString().substring(0,16));
        return chettaView;

    }

}
