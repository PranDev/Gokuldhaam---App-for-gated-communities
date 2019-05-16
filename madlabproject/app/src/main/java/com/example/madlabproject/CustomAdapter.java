package com.example.madlabproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

 class contact{

    String name;
    String bid;

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    int Price;


    public contact(String bid, String name, int price) {
        this.name = name;
        this.bid=bid;
        this.name=name;

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

public class CustomAdapter extends ArrayAdapter<contact> {
    private List<contact> contactlist;
    private Context context;

    public CustomAdapter(List<contact> contactlist,Context context) {
        super(context, R.layout.list_view1,contactlist);
        this.contactlist=contactlist;
        this.context=context;

    }

    private static class foodHolder{
        public TextView contactName;
        public TextView tprice;
        public TextView Bid1;


    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        foodHolder holder = new foodHolder();

        if(convertView==null)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.list_view1,parent,false);

            holder.contactName = (TextView) v.findViewById(R.id.textView9);
            holder.tprice=v.findViewById(R.id.textView10);
            holder.Bid1=v.findViewById(R.id.textView11);




        }
        else
        {
            holder = (foodHolder) v.getTag();

        }
        contact c = contactlist.get(position);
        holder.contactName.setText(c.getName());
        holder.tprice.setText("Price:300");
        holder.Bid1.setText(c.getBid());

        return v;

    }


}

