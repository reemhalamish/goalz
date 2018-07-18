package com.reem.halamish.goalz.repo

import com.reem.halamish.goalz.models.UserDetails
import io.reactivex.subjects.PublishSubject


class Repository {
    val userDetails = PublishSubject.create<UserDetails>()

    companion object {
        @JvmField val instance = Repository()
    }
}