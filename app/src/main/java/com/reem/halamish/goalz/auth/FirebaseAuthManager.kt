package com.reem.halamish.goalz.auth

import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.reem.halamish.goalz.models.Optional
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject


class FirebaseAuthManager {
    private val _connectedUser: BehaviorSubject<Optional<FirebaseUser>> = BehaviorSubject.create()
    val connectedUser: Observable<Optional<FirebaseUser>>
            get() = _connectedUser

    private val auth: FirebaseAuth
        get() = FirebaseAuth.getInstance()


    private val facebookLogin: FacebookLoginConnector
    private val facebookLogout: FacebookLogoutConnector

    init {
        _connectedUser.onNext(Optional(auth.currentUser))

        facebookLogin = FacebookLoginConnector()
        facebookLogin.loginState.subscribe { token ->

            val credential = FacebookAuthProvider.getCredential(token.token)

            auth.signInWithCredential(credential)
                    .addOnCompleteListener {
                        _connectedUser.onNext(Optional(auth.currentUser))
                    }
        }

        facebookLogout = FacebookLogoutConnector(connectedUser.filter { it.isEmpty }.map { Unit })
    }

    fun logout() {
        auth.signOut()
        _connectedUser.onNext(Optional(null))
    }


    companion object {
        @JvmField val instance = FirebaseAuthManager()
    }
}