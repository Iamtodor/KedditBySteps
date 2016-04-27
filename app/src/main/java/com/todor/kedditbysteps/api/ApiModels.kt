package com.todor.kedditbysteps.api

class RedditNewsResponse(val data: RedditDataNewsResponse)

class RedditNews(
        val after: String?,
        val before: String?,
        val children: RedditChildrenResponse
)

class RedditChildrenResponse(
        val data: RedditDataNewsResponse)

class RedditDataNewsResponse(
    val author: String,
    val title: String,
    val num_comments: Int,
    val created: Long,
    val thumbnail: String,
    val url: String
)


