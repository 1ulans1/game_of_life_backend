package com.softserve.game_of_life.modele

import com.softserve.game_of_life.utils.Constants

class Prey(
    private var timeToReproduce: Int = Constants.randomTimeToReproduce(),
    ocean: Ocean,
    location: Location
) : Cell(ocean, location) {

    override fun getDefaultImage() = "\uD83D\uDFE9"

    override fun process() {
        decrementTimeToReproduce()
        moveRandom()
        if (timeToReproduce <= 0) {
            reproduce()
            timeToReproduce = Constants.randomTimeToReproduce()
        }
    }

    private fun decrementTimeToReproduce() {
        timeToReproduce--
    }

    override fun createNewCell(location: Location): Cell {
        return Prey(ocean = ocean, location = location)
    }
}
