package com.madscientists.algoliademo.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.madscientists.algoliademo.R;
import com.madscientists.algoliademo.model.Hits;
import com.madscientists.algoliademo.ui.Activity_WebView;
import com.madscientists.algoliademo.util.Utils;

import java.text.ParseException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by madscientist on 7/10/17.
 */

public class Adapter_SearchResults extends RecyclerView.Adapter<Adapter_SearchResults.ViewHolder> {

    private Context context;
    private List<Hits>hitsList;
    public Adapter_SearchResults(Context context, List<Hits> hitsList) {
        this.context = context;
        this.hitsList = hitsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_search_result, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Hits hits = hitsList.get(position);

        holder.authorText.setText(String.format("Author: %s",hits.getAuthor()));
        holder.commentsText.setText(String.format("%s comment(s)",Utils.intFormater((double)hits.getNum_comments(), 0)));
        if (!TextUtils.isEmpty(hits.getStory_text())){
            holder.contentText.setVisibility(View.VISIBLE);
            holder.contentText.setText(Html.fromHtml(hits.getStory_text()));
        }else{
            holder.contentText.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(hits.getCreated_at())) {
            try {
                holder.createdAt.setText(String.format("Article date: %s", Utils.formatDate(hits.getCreated_at())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            holder.createdAt.setVisibility(View.VISIBLE);
        } else {
            holder.createdAt.setVisibility(View.GONE);
        }
        holder.titleText.setText(hits.getTitle());
        holder.rowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity_WebView.initAndStart(context,hits.getTitle(),hits.getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return hitsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rsr_authorText)
        TextView authorText;
        @BindView(R.id.rsr_comments_text)
        TextView commentsText;
        @BindView(R.id.rsr_content_text)
        TextView contentText;
        @BindView(R.id.rsr_rowLayout)
        CardView rowLayout;
        @BindView(R.id.rsr_title_text)
        TextView titleText;
        @BindView(R.id.rsr_createdAt)
        TextView createdAt;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
