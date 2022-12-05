package com.softserve.game_of_life.modele

class Obstacle : Cell() {

    override fun getDefaultImage(): String = "⬛"

    override fun process() {}
}
