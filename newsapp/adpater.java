package com.wowvio.newsapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class adpater extends RecyclerView.Adapter<adpater.viewHolder> {

    List<article> articles;

    public adpater(List<article> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        article articl =  articles.get(position);
        
        holder.title.setText(articl.getTitle());
        holder.author.setText(articl.getAuthor());
        Glide.with(holder.itemView.getContext()).load(articl.getUrlToImage()).into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(holder.itemView.getContext(), open.class);
                intent.putExtra("url", articl.getUrl());

                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView author;
        ImageView image;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
            image = itemView.findViewById(R.id.image);
        }
    }
}