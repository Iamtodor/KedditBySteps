package com.todor.kedditbysteps

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.todor.kedditbysteps.commons.InfiniteScrollListener
import com.todor.kedditbysteps.commons.RedditNews
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

    companion object {
        private val KEY_REDDIT_NEWS = "redditNews"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.news_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        news_list.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
            addOnScrollListener(InfiniteScrollListener({requestNews()}, linearLayout))
        }

        initAdapter()

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_REDDIT_NEWS)) {
            redditNews = savedInstanceState.get(KEY_REDDIT_NEWS) as RedditNews

        }
    }

    private fun requestNews() {
        val subscription = newsManager.getNews(redditNews?.after ?: "")
                .subscribeOn(Schedulers.io())
                .subscribe({
                    retrievedNews ->
                    redditNews = retrievedNews
                    (news_list.adapter as NewsAdapter).addNews(retrievedNews.news)
                },
                        { error ->
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

