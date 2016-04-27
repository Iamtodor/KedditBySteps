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

    fun getNews(after: String, limit: String = "10"): Observable<RedditNewsResponse> {
        return Observable.create {
            subscriber ->
            val callResponse = api.getTop(after, limit)
            val response = callResponse.execute()
            if(response.isSuccessful) {
                val dataResponse = response.body().data
                val news = dataResponse.children.map {
                    val item = it.data
                    RedditNewsItem(item.author, item.title, item.num_comments, item.created, item.thumbnail,
                            item.thumbnail)
                }
                val redditNews = RedditNews(
                        dataResponse.after ?: "",
                        dataResponse.before ?: "",
                        news)

                subscriber.onNext(redditNews)
                subscriber.onCompleted()
            } else {
                subscriber.onError(
                        Throwable(response.message())
                )
            }
        }
    }

}
