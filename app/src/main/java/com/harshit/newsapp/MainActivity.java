package com.harshit.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    Intro_activity ob;

    ViewPager viewPager;
    CardAdapter cardAdapter;
    List<CardModel> list;
    List<Article> article = new ArrayList<>();

    private ViewModel viewModel;

    private Observer<ResponseAPI> NewsObserver = new Observer<ResponseAPI>() {
        @Override
        public void onChanged(ResponseAPI responseAPI) {
            if (responseAPI != null) {
                article = responseAPI.getArticles();
                Log.v(article.get(0).getUrlToImage(),article.get(0).getUrlToImage());
                list.add(new CardModel(article.get(0).getUrlToImage(),article.get(0).getTitle(),article.get(0).getDescription()));
                list.add(new CardModel(article.get(1).getUrlToImage(),article.get(1).getTitle(),article.get(1).getDescription()));
                list.add(new CardModel(article.get(2).getUrlToImage(),article.get(2).getTitle(),article.get(2).getDescription()));

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
        ob.nAuth = FirebaseAuth.getInstance();
        list = new ArrayList<>();
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewPager = findViewById(R.id.view_pager);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ob.nAuth.signOut();
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