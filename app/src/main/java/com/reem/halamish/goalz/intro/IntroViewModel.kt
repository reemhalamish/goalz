package com.reem.halamish.goalz.intro

import android.arch.lifecycle.*
import android.content.Intent
import android.support.v4.app.FragmentActivity
import com.facebook.CallbackManager
import com.google.firebase.auth.FirebaseUser
import com.reem.halamish.goalz.models.UserDetails
import com.reem.halamish.goalz.repo.Repository
import io.reactivex.BackpressureStrategy


class IntroViewModel

@JvmOverloads constructor(private val repo: Repository = Repository.instance)

    : ViewModel() {



    val userDetails: LiveData<UserDetails>
        get() =     LiveDataReactiveStreams
                .fromPublisher(repo.userDetails.toFlowable(BackpressureStrategy.LATEST))

    val connectedUser: LiveData<FirebaseUser?>

        get() = Transformations.map(LiveDataReactiveStreams.fromPublisher(
                repo.connectedUser.toFlowable(BackpressureStrategy.LATEST)
        )) { it.value }

//    val introProcessFinished: LiveData<Unit>
//        get() {
//            return  LiveDataReactiveStreams.fromPublisher(
//                    Observables.combineLatest(repo.connectedUser, repo.userDetails)
//                            .map { Unit }
//                            .toFlowable(BackpressureStrategy.BUFFER))
//        }


//    private val callbackManager: CallbackManager =

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        CallbackManager.Factory.create().onActivityResult(requestCode, resultCode, data)
    }

    fun setDetails(userDetails: UserDetails) {
        repo.userDetails.onNext(userDetails)
    }


    companion object {
        @JvmStatic fun get(a: FragmentActivity) = ViewModelProviders.of(a).get(IntroViewModel::class.java)
    }

}