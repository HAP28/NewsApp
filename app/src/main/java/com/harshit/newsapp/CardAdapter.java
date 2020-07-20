package com.harshit.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CardAdapter extends PagerAdapter {

    private List<CardModel> list;
    private LayoutInflater layoutInflater;
    private Context context;

    public CardAdapter(List<CardModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item, container, false);

        ImageView imageView;
        TextView title, src,date;

        imageView = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title_card);
        src = view.findViewById(R.id.src);
        date = view.findViewById(R.id.date);

        Picasso.get().load(Uri.parse(list.get(position).getImage())).into(imageView);
        title.setText(list.get(position).getTitle());
        src.setText(list.get(position).getSrc());
        date.setText(list.get(position).getDate());


        container.addView(view,0);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,WebActivity.class);
                intent.putExtra("url",MainActivity.article.get(position).getUrl());
                context.startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
