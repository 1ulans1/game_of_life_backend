package com.softserve.game_of_life.modele

data class Location(
    val row: Int,
    val colum: Int
) {
    operator fun plus(other: Location) = Location(row + other.row, colum + other.colum)
}
