package com.softserve.game_of_life.modele

open class Cell {
    open fun getDefaultImage(): String = "⬜️"
    open fun process() {}
}
