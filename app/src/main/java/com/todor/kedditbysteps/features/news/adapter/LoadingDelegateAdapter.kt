package com.todor.kedditbysteps.features.news.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.todor.kedditbysteps.R
import com.todor.kedditbysteps.commons.adapter.ViewType
import com.todor.kedditbysteps.commons.adapter.ViewTypeDelegateAdapter
import com.todor.kedditbysteps.commons.extentions.inflate

class LoadingDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    }

    override fun onCreateViewHolder(parent: ViewGroup) = LoadingViewHolder(parent)

    class LoadingViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.news_item_loading)) {
    }
}
