package com.harshit.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    Intro_activity ob;
    Timer timer;
    ViewPager viewPager;
    CardAdapter cardAdapter;
    List<CardModel> list;
    List<Article> article = new ArrayList<>();
    int currentPage = 0;
    private ViewModel viewModel;

    private Observer<ResponseAPI> NewsObserver = new Observer<ResponseAPI>() {
        @Override
        public void onChanged(ResponseAPI responseAPI) {
            if (responseAPI != null) {
                article = responseAPI.getArticles();
                for(int i=2;i<7;i++) {
                    list.add(new CardModel(article.get(i).getUrlToImage(), article.get(i).getTitle(), article.get(i).getDescription()));
                }

                cardAdapter = new CardAdapter(list, MainActivity.this);
                viewPager.setAdapter(cardAdapter);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button signOut = (Button) findViewById(R.id.sign_out_btn);
        ob = new Intro_activity();
        ob.mAuth = FirebaseAuth.getInstance();
        list = new ArrayList<>();
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewPager = findViewById(R.id.view_pager);
        viewModel.getNews();

        final Handler handler = new Handler();
        final Runnable Update = () -> {
            if (currentPage == 5) {
                currentPage = 0;
            }
            viewPager.setCurrentItem(currentPage++, true);
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 500, 3000);


        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ob.mAuth.signOut();
                AuthUI.getInstance().signOut(getApplicationContext());
                Toast.makeText(MainActivity.this, "Good Bye", Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this, Intro_activity.class);
                startActivity(i);
                finish();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        viewModel.News().observe(this,NewsObserver);
    }

}