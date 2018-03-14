package com.debajyotibasak.testnews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.debajyotibasak.testnews.R;
import com.debajyotibasak.testnews.model.NewsArticle;

import java.util.List;

/**
 * Created by debajyotibasak on 11/03/18.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private Context context;
    private List<NewsArticle> newsArticles;

    public NewsAdapter(List<NewsArticle> newsArticles, Context context) {
        this.newsArticles = newsArticles;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_article_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txvTitle.setText(newsArticles.get(position).getTitle());
        holder.txvBody.setText(newsArticles.get(position).getDescription());
    }

    public void addItems(List<NewsArticle> items) {
        this.newsArticles = items;
        notifyDataSetChanged();
    }

    /*public void replaceItems(List<NewsArticle> items) {
        this.newsArticles = items;
    }*/

    @Override
    public int getItemCount() {
        return newsArticles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txvTitle, txvBody;

        public MyViewHolder(View view) {
            super(view);
            txvTitle = view.findViewById(R.id.txt_news_title);
            txvBody = view.findViewById(R.id.txt_news_body);
        }
    }
}
