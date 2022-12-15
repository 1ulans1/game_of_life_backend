package com.softserve.game_of_life.modele

import com.softserve.game_of_life.utils.Constants

open class Prey(
    var timeToReproduce: Int = Constants.defaultTimeToReproduce,
    var alive: Boolean = true,
    val ocean: Ocean,
    var location: Location
) : Cell {

    private val random = Random.random

    private val help = listOf(
        Location(-1, 0),
        Location(0, -1),
        Location(0, 1),
        Location(0, 0),
        Location(1, 0),
    )

    override fun getDefaultImage() = "\uD83D\uDFE9"
    override fun process() {
        iteration()
        reproduction()
        if (alive) {
            val newLocation = normalizeLocation(
                Location(
                    random.nextInt(3) - 1 + location.row,
                    random.nextInt(3) - 1 + location.colum
                )
            )

            ocean.ocean[newLocation.row][newLocation.colum] = Empty()
            changeLocation(newLocation)
        } else {
            dead();
        }
    }

    fun reproduction() {
        if (timeToReproduce <= 0) {
            help.shuffled().forEach {
                val currentLocation = normalizeLocation(Location(it.row + location.row, it.colum + location.colum))
                if (ocean.ocean[currentLocation.row][currentLocation.colum] is Empty) {

                    ocean.ocean[currentLocation.row][currentLocation.colum] =
                        Predator(ocean = ocean, location = currentLocation)
                }
            }
            timeToReproduce = Constants.defaultTimeToReproduce

        }
    }

    fun dead() {
        ocean.ocean[location.row][location.colum] = Empty.instance
    }

    private fun changeLocation(location: Location) {
        ocean.ocean[this.location.row][this.location.colum] = Empty()
        ocean.ocean[location.row][location.colum] = this
        this.location = Location(location.row, location.colum)
    }

    fun iteration() {

        timeToReproduce--
    }

    fun normalizeLocation(location: Location): Location {
        return Location(
            if (location.row < 0) {
                ocean.ocean.size - 1
            } else {
                location.row % ocean.ocean.size
            }, if (location.colum < 0) {
                ocean.ocean[0].size - 1
            } else {
                location.colum % ocean.ocean[0].size
            }
        )
    }

    fun clean() {
        ocean.ocean[location.row][location.colum] = Empty()
    }
}
