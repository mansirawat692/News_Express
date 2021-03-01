package com.example.newsexpress;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsexpress.model.Articles;
import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    List<Articles> articles;

    public Adapter(Context context,List<Articles> articles) {
        this.context=context;
        this.articles=articles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Articles a=articles.get(position);
        holder.t_title.setText(a.getTitle());
        holder.t_source.setText(a.getSource().getName());
        holder.t_date.setText("\u2022"+Datetime(a.getPublishedAt()));
        Picasso.get().load(a.getUrlToImage()).into(holder.i_image);


        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,Details.class);
               // i.putExtra("title", a.getTitle());
               // i.putExtra("time", Datetime(a.getPublishedAt()));
                //i.putExtra("source", a.getSource().getName());
                //i.putExtra("img", a.getUrlToImage());
                i.putExtra("url", a.getUrl());
                //i.putExtra("des", a.getDescription());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {

        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView t_title,t_source,t_date;
        public ImageView i_image;
        public CardView cv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            t_title=itemView.findViewById(R.id.newstitle);
            t_source=itemView.findViewById(R.id.source);
            t_date=itemView.findViewById(R.id.time);
            cv=itemView.findViewById(R.id.cv1);
            i_image=itemView.findViewById(R.id.image);
        }
    }


    public String Datetime(String t){
        PrettyTime prettyTime=new PrettyTime(new Locale(getCountry()));
        String time=null;
        try{
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:",Locale.ENGLISH);
            Date date= simpleDateFormat.parse(t);
            time=prettyTime.format(date);
        }catch (ParseException e){
            Toast.makeText(context,  e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return time;
    }


    public String getCountry(){
        Locale locale=Locale.getDefault();
        String country= locale.getCountry();
        return country.toLowerCase();
    }
}
