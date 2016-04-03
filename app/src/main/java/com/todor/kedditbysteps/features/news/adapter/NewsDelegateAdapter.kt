package com.todor.kedditbysteps.features.news.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.todor.kedditbysteps.R
import com.todor.kedditbysteps.commons.RedditNewsItem
import com.todor.kedditbysteps.commons.adapter.ViewType
import com.todor.kedditbysteps.commons.adapter.ViewTypeDelegateAdapter
import com.todor.kedditbysteps.commons.extentions.getFriendlyTime
import com.todor.kedditbysteps.commons.extentions.inflate
import com.todor.kedditbysteps.commons.extentions.loadImg
import kotlinx.android.synthetic.main.news_item.view.*

class NewsDelegateAdapter: ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return NewsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as NewsViewHolder
        holder.bind(item as RedditNewsItem)
    }

    class NewsViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
            parent.inflate(R.layout.news_item)) {

        fun bind(item: RedditNewsItem) = with(itemView) {
            img_thumbnail.loadImg(item.thumbnail)
            description.text = item.title
            author.text = item.author
            comments.text = "${item.numComments} comments"
            time.text = item.created.getFriendlyTime()
        }
    }

}

