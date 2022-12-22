package com.softserve.game_of_life.modele

import com.softserve.game_of_life.utils.Constants

class Predator(
    private var timeToReproduce: Int = Constants.randomTimeToReproduce(),
    private var timeToFeed: Int = Constants.randomDefaultTimeToFeed(),
    private var alive: Boolean = true,
    ocean: Ocean,
    location: Location
) : Cell(ocean, location) {

    override fun getDefaultImage() = "\uD83D\uDFE5"

    override fun process() {
        iteration()

        if (!alive) {
            dead()
            return
        }

        if (!eatPrey()) {
            moveRandom()
        }

        if (timeToReproduce <= 0) {
            reproduce()
            timeToReproduce = Constants.randomTimeToReproduce()
        }

    }

    private fun eatPrey(): Boolean {
        return neighbors.shuffled().map { normalizeLocation(it + location) }
            .firstOrNull { ocean.ocean!![it.row][it.colum] is Prey }
            ?.let {
                changeLocation(it)
                timeToFeed = Constants.randomDefaultTimeToFeed()
                true
            } ?: false
    }

    override fun createNewCell(location: Location) = Predator(
        ocean = ocean,
        location = location
    )

    private fun iteration() {
        timeToFeed--
        timeToReproduce--

        if (timeToFeed <= 0) {
            alive = false
        }
    }
}
