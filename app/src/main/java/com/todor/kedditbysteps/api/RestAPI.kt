package com.todor.kedditbysteps.api

import com.todor.kedditbysteps.commons.RedditNewsItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import rx.Observable

class RestAPI {

    private val api: RedditApi
    private val baseURL: String = "https://www.reddit.com"

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        api = retrofit.create(RedditApi::class.java)
    }

    fun getNews(after: String, limit: String = "10"): Call<RedditNewsResponse> {
        return api.getTop(after, limit)
    }

}
