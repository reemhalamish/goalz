package com.reem.halamish.goalz.intro

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.facebook.AccessToken
import com.google.firebase.auth.FirebaseUser
import com.reem.halamish.goalz.models.UserDetails
import com.reem.halamish.goalz.repo.Repository
import io.reactivex.BackpressureStrategy
import io.reactivex.functions.Predicate
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.combineLatest
import java.util.*

class IntroViewModel(val repo: Repository = Repository.instance): ViewModel() {
    val userDetails: LiveData<UserDetails>
        get() = LiveDataReactiveStreams
                .fromPublisher(repo.userDetails.toFlowable(BackpressureStrategy.LATEST))

    val connectedUser: LiveData<FirebaseUser>
        get() = LiveDataReactiveStreams
                .fromPublisher(repo.connectedUser.toFlowable(BackpressureStrategy.LATEST))

    val introProcessFinished: LiveData<Unit>
        get() {
            return  LiveDataReactiveStreams.fromPublisher(
                    Observables.combineLatest(repo.connectedUser, repo.userDetails)
                            .map { Unit }
                            .toFlowable(BackpressureStrategy.BUFFER))
        }

    fun connectUser(token: AccessToken) {
        // todo
    }

    fun setDetails(userDetails: UserDetails) {
        repo.userDetails.onNext(userDetails)
    }
}