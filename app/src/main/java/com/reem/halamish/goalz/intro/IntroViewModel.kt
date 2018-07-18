package com.reem.halamish.goalz.intro

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.reem.halamish.goalz.models.UserDetails
import com.reem.halamish.goalz.repo.Repository
import io.reactivex.BackpressureStrategy

class IntroViewModel(val repo: Repository = Repository.instance): ViewModel() {
    var userDetails: LiveData<UserDetails> = LiveDataReactiveStreams.fromPublisher(repo.userDetails.toFlowable(BackpressureStrategy.LATEST))

    fun setDetails(userDetails: UserDetails) {
        repo.userDetails.onNext(userDetails)
    }
}