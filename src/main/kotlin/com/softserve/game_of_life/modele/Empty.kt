package com.softserve.game_of_life.modele

class Empty : Cell {
    override fun getDefaultImage(): String = "⬜️"
    override fun process() {}

    companion object {
        val instance = Empty()
    }
}
