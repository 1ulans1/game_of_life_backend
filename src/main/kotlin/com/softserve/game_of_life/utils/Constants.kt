package com.softserve.game_of_life.utils

object Constants {
    val defaultTimeToReproduce = 7..10
    val defaultTimeToFeed = 7..10
    fun randomTimeToReproduce() = defaultTimeToReproduce.random()
    fun randomDefaultTimeToFeed() = defaultTimeToFeed.random()
}