package com.example.newsexpress;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsexpress.model.Articles;
import com.example.newsexpress.model.headlines;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
RecyclerView rv;
TextView tv1;
Button search;
Adapter adapter;
SwipeRefreshLayout swipeRefreshLayout;
public String query;
List<Articles> articles=new ArrayList<>();
final String api="7a7a22a82c2a4cb6b9a4d9aef49f3962";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String country=getCountry();
        rv=findViewById(R.id.rv1);
        tv1=findViewById(R.id.name);
        search=findViewById(R.id.search);
        rv.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout=findViewById(R.id.swipe);
        query=tv1.getText().toString();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                retriveJson("",country, api);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!query.equals("")){
                    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            retriveJson(query,country, api);
                        }
                    });
                }
                else{
                    retriveJson("", country, api);
                }
            }
        });



        retriveJson("",country, api);

    }

    public  void retriveJson(String query,String country,String api){
swipeRefreshLayout.setRefreshing(true);
ApiInteface apiInteface=ApiClient.ApiClient().create(ApiInteface.class);
        Call<headlines> call;

        if(!query.equals("")){
            call=apiInteface.getSpecificData(query, api);
        }
        else{
            call=apiInteface.getheadlines(country, api);
        }
        call.enqueue(new Callback<headlines>() {
            @Override
            public void onResponse(Call<headlines> call, Response<headlines> response) {
                if(response.isSuccessful() && response.body().getArticles() != null){
                    swipeRefreshLayout.setRefreshing(false);
                    articles.clear();
                    articles=response.body().getArticles();
                    adapter=new Adapter(MainActivity.this, articles);
                    rv.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<headlines> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(true);
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
       // rv.setAdapter(adapter);


    }


    public String getCountry(){
        Locale locale=Locale.getDefault();
        String country=locale.getCountry();
        return country.toLowerCase();
    }


 /*   @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }*/
}
//