package com.softserve.game_of_life.modele

interface Cell {

    fun getDefaultImage(): String
    fun process()

}
