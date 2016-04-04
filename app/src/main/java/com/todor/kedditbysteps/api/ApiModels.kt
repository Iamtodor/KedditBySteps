package com.todor.kedditbysteps.api

class RedditNewsResponse(val data: RedditDataResponse)

class RedditDataResponse(
        val children: List<RedditChildrenResponse>,
        val after: String?,
        val before: String?
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


