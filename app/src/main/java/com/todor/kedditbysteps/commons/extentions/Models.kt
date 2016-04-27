package com.todor.kedditbysteps.commons.extentions

import com.todor.kedditbysteps.commons.adapter.AdapterConstant
import com.todor.kedditbysteps.commons.adapter.ViewType

data class RedditNews(
        val after: String,
        val before: String,
        val news: List<RedditNewsItem>)

data class RedditNewsItem(
        val author: String,
        val title: String,
        val num_comments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String
) : ViewType {
    override fun getViewType() = AdapterConstant.NEWS
}