package com.reem.halamish.goalz.repo

import com.reem.halamish.goalz.auth.FirebaseAuthManager
import com.reem.halamish.goalz.models.UserDetails
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject


class Repository(authManager: FirebaseAuthManager = FirebaseAuthManager.instance) {
    val userDetails: PublishSubject<UserDetails> = PublishSubject.create<UserDetails>()
    val connectedUser = authManager.connectedUser

    companion object {
        @JvmField val instance = Repository()
    }
}