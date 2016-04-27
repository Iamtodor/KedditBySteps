package com.todor.kedditbysteps

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.todor.kedditbysteps.api.RedditNews
import com.todor.kedditbysteps.commons.InfiniteScrollListener
import com.todor.kedditbysteps.commons.RedditNewsItem
import com.todor.kedditbysteps.commons.RxBaseFragment
import com.todor.kedditbysteps.commons.extentions.inflate
import com.todor.kedditbysteps.features.news.NewsManager
import com.todor.kedditbysteps.features.news.adapter.NewsAdapter
import kotlinx.android.synthetic.main.news_fragment.*
import rx.schedulers.Schedulers

class NewsFragment : RxBaseFragment() {

    private var redditNews: RedditNews? = null
    private val LIMIT: String = "10"
    private val newsManager by lazy {
        NewsManager()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.news_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        news_list.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(context)
        news_list.layoutManager = linearLayoutManager
        news_list.addOnScrollListener(InfiniteScrollListener({requestNews()}, linearLayoutManager))

        initAdapter()

        if (savedInstanceState == null) {
            requestNews()
        }
    }

    private fun requestNews() {
        val subscription = newsManager.getNews(redditNews?.after ?: "")
                .subscribeOn(Schedulers.io())
                .subscribe({
                    retrievedNews ->
                    redditNews = retrievedNews
                },
                        {
                            error ->
                            Snackbar.make(news_list, error.message ?: "", Snackbar.LENGTH_SHORT).show()
                            error.printStackTrace()
                        }
                )

        subscriptions.add(subscription)
    }

    private fun initAdapter() {
        if (news_list.adapter == null)
            news_list.adapter = NewsAdapter()
    }
}

