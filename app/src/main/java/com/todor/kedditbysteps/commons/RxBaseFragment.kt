package com.todor.kedditbysteps.commons

import android.support.v4.app.Fragment
import rx.subscriptions.CompositeSubscription

open class RxBaseFragment(): Fragment() {

    var subscriptions = CompositeSubscription()

    override fun onResume() {
        super.onResume()
        subscriptions = CompositeSubscription()
    }

    override fun onPause() {
        if(!subscriptions.isUnsubscribed) {
            subscriptions.unsubscribe()
            subscriptions.clear()
        }
        super.onPause()
    }
}
