package com.example.newsexpress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {
public TextView title1,tim1,source1,des;
public WebView webView;
public ImageView imageView;
public ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //title1=findViewById(R.id.newstitle1);
      //  source1=findViewById(R.id.source1);
       // tim1=findViewById(R.id.time1);
       // des=findViewById(R.id.des);
       // imageView=findViewById(R.id.image1);
        webView=findViewById(R.id.webview);
        progressBar=findViewById(R.id.loader2);
        progressBar.setVisibility(View.VISIBLE);

        Intent i=getIntent();
        //String title=i.getStringExtra("title");
        //String description=i.getStringExtra("des");
        //String time=i.getStringExtra("time");
        //String source=i.getStringExtra("source");
        //String img=i.getStringExtra("imh");
       String url=i.getStringExtra("url");
       // String title=i.getStringExtra("title");

       // title1.setText(title);
       // source1.setText(source);
        //tim1.setText(time);
        //des.setText(description);
        //Picasso.get().load(img).into(imageView);
        //source1.setText(source);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

        if(webView.isShown()){
            progressBar.setVisibility(View.INVISIBLE);
        }

    }
}