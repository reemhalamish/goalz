package com.reem.halamish.goalz.auth

import com.facebook.login.LoginManager
import io.reactivex.Observable


class FacebookLogoutConnector(logout: Observable<Unit>) {
    init {
        logout.subscribe {
            LoginManager.getInstance().logOut()
        }
    }
}