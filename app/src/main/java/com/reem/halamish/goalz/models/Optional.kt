package com.reem.halamish.goalz.models

data class Optional<M>(val value : M?){
    val isEmpty get() = value == null
}