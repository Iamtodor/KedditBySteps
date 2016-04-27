package com.todor.kedditbysteps.features.news

import com.todor.kedditbysteps.api.RestAPI
import com.todor.kedditbysteps.api.RedditNews
import com.todor.kedditbysteps.commons.RedditNewsItem
import rx.Observable

class NewsManager(private val api: RestAPI = RestAPI()) {

    fun getNews(after: String, limit: String = "10"): Observable<RedditNews> {
        return Observable.create {
            subscriber ->
            val callResponse = api.getNews(after, limit)
            val response = call

            if (response.isSuccessful) {
                val news = response.body().data.children.map {
                    val item = it.data
                    RedditNewsItem(item.author, item.title, item.num_comments,
                            item.created, item.thumbnail, item.url)
                }
                subscriber.onNext(news)
            } else {
                subscriber.onError(Throwable(response.message()))
            }

        }
    }

}
