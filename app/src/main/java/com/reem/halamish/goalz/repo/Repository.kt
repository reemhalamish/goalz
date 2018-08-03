package com.reem.halamish.goalz.repo

import com.reem.halamish.goalz.auth.FirebaseAuthManager
import com.reem.halamish.goalz.models.UserDetails
import io.reactivex.subjects.BehaviorSubject


class Repository(private val authManager: FirebaseAuthManager = FirebaseAuthManager.instance) {
    val userDetails: BehaviorSubject<UserDetails> = BehaviorSubject.create<UserDetails>()

    val connectedUser get() = authManager.connectedUser

//    private val connectedUserWithBoolean = authManager.isUserConnected.zipWith(authManager.connectedUser)
//
//    val connectedUser: LiveData<FirebaseUser?>
//            = LiveDataReactiveStreams.fromPublisher(
//            connectedUserWithBoolean
//                    .flatMap { if (it.first) { Observable.just(it.second) } else Observable.empty() }
//                    .toFlowable(BackpressureStrategy.LATEST)
//    )
//    val connectedUser
//            get() = authManager.connectedUser
//    val isUserConnected
//            get() = authManager.isUserConnected

    companion object {
        @JvmField val instance = Repository()
    }
}