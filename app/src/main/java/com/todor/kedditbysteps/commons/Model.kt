package com.todor.kedditbysteps.commons

import com.todor.kedditbysteps.commons.adapter.AdapterConstant
import com.todor.kedditbysteps.commons.adapter.ViewType

data class RedditNewsItem(
        val author: String,
        val title: String,
        val numComments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String) : ViewType {
    override fun getViewType() = AdapterConstant.NEWS


}

