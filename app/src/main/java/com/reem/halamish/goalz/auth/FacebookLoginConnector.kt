package com.reem.halamish.goalz.auth

import com.facebook.AccessToken
import com.facebook.AccessTokenTracker
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.reem.halamish.goalz.extensions.logDebug
import com.reem.halamish.goalz.extensions.logError
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject


class FacebookLoginConnector: AccessTokenTracker(), FacebookCallback<LoginResult> {

    private fun AccessToken?.onlyIfIsOk(): AccessToken? = if (this != null && !this.isExpired) this else null


    override fun onSuccess(result: LoginResult?) {
        // tracker will take care
        logDebug("success! $result")
    }

    override fun onCancel() {
        logDebug("canceled")
    }

    override fun onError(error: FacebookException?) {
        logError("facebook error", error ?: RuntimeException("error is isEmpty"))
    }


    private val _loginState = BehaviorSubject.create<AccessToken>()
    val loginState
        get() = _loginState as Observable<AccessToken>

    override fun onCurrentAccessTokenChanged(oldAccessToken: AccessToken?, currentAccessToken: AccessToken?) {
        logDebug("access token changed")
        currentAccessToken.onlyIfIsOk()?.let { _loginState.onNext(it) }
    }

    init {
        startTracking()
        AccessToken.getCurrentAccessToken().onlyIfIsOk()?.let { _loginState.onNext(it) }
    }
}