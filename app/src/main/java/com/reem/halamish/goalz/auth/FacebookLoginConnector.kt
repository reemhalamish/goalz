package com.reem.halamish.goalz.auth

import com.facebook.AccessToken
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject


class FacebookLoginConnector {
    val loginState = BehaviorSubject.create<AccessToken>()
    init { run {
        val token = AccessToken.getCurrentAccessToken() ?: return@run
        if (token.isExpired) return@run

        loginState.onNext(token)
    } }

    companion object {
        @JvmField val instance = FacebookLoginConnector()
    }
}