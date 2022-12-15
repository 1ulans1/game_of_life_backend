package com.softserve.game_of_life.modele

import com.softserve.game_of_life.utils.Constants

class Predator(
    var timeToReproduce: Int = Constants.defaultTimeToReproduce,
    var timeToFeed: Int = Constants.defaultTimeToFeed,
    var alive: Boolean = true,
    val ocean: Ocean, var location: Location
) : Cell {

    private val random = Random.random

    private val help = listOf(
        Location(-1, 0),
        Location(0, -1),
        Location(0, 1),
        Location(0, 0),
        Location(1, 0),
    )

    override fun getDefaultImage() = "\uD83D\uDFE5"
    override fun process() {
        iteration()
        reproduction()
        if (alive) {
            var eatPrey = false
            help.forEach {
                val newLocation = normalizeLocation(Location(location.row + it.row, location.colum + it.colum))

                if (ocean.ocean[newLocation.row][newLocation.colum] is Prey) {
                    changeLocation(newLocation)
                    eatPrey = true
                }
            }

            if (!eatPrey) {
                val newLocation = normalizeLocation(
                    Location(
                        random.nextInt(2) - 1 + location.row,
                        random.nextInt(2) - 1 + location.colum
                    )
                )
                changeLocation(newLocation)
            }
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

    private fun normalizeLocation(location: Location): Location {
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

    private fun changeLocation(location: Location) {
        ocean.ocean[this.location.row][this.location.colum] = Empty()
        ocean.ocean[location.row][location.colum] = this
        this.location = Location(location.row, location.colum)
    }

    private fun iteration() {

        timeToFeed--
        timeToReproduce--
        if (timeToFeed <= 0) {
            alive = false
        }
    }
}
