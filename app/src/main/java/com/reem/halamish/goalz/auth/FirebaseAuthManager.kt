package com.reem.halamish.goalz.auth

import com.google.firebase.auth.*
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


class FirebaseAuthManager(private val facebook: FacebookLoginConnector) {
    private val _connectedUser: PublishSubject<FirebaseUser> = PublishSubject.create()
    var connectedUser: Observable<FirebaseUser> = _connectedUser


    init {
        val auth = FirebaseAuth.getInstance()

        auth.currentUser?.let { _connectedUser.onNext(it) }

        facebook.loginState.subscribe { token ->

            val credential = FacebookAuthProvider.getCredential(token.token)

            auth.signInWithCredential(credential)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            auth.currentUser?.let { _connectedUser.onNext(it) }
                        }
                    }
        }
    }
}