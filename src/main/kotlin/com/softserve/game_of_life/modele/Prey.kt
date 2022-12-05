package com.softserve.game_of_life.modele

open class Prey(val timeToReproduce: Int, val timeToFeed: Int) : Cell() {

    override fun getDefaultImage() = "\uD83D\uDFE9"
    override fun process() {}
}
