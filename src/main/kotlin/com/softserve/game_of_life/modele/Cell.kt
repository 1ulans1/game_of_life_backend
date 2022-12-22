package com.softserve.game_of_life.modele

abstract class Cell(
    protected open val ocean: Ocean,
    protected var location: Location
) {
    protected val neighbors = listOf(
        Location(-1, 0),
        Location(0, -1),
        Location(0, 1),
        Location(0, 0),
        Location(1, 0)
    )

    abstract fun getDefaultImage(): String
    abstract fun process()
    protected abstract fun createNewCell(location: Location): Cell

    protected fun changeLocation(location: Location) {
        val normalizedLocation = normalizeLocation(location)
        ocean.ocean!![this.location.row][this.location.colum] = Empty(ocean, normalizedLocation)
        ocean.ocean!![normalizedLocation.row][normalizedLocation.colum] = this
        this.location = normalizedLocation
    }

    protected fun moveRandom() {
        val emptyCell = neighbors.shuffled()
            .map { normalizeLocation(it + location) }
            .firstOrNull { ocean.ocean!![it.row][it.colum] is Empty }

        if (emptyCell != null) {
            changeLocation(emptyCell)
        }
    }

    protected fun reproduce() {
        val emptyCell = neighbors.shuffled()
            .map { normalizeLocation(it + location) }
            .firstOrNull { ocean.ocean!![it.row][it.colum] is Empty }

        emptyCell?.let {
            val newCell = createNewCell(it)
            ocean.ocean!![it.row][it.colum] = newCell
        }
    }

    protected fun dead() {
        ocean.ocean!![location.row][location.colum] = Empty(ocean, location)
    }

    protected fun normalizeLocation(location: Location): Location {
        val row = (location.row + ocean.ocean!!.size) % ocean.ocean!!.size
        val colum = (location.colum + ocean.ocean!![0].size) % ocean.ocean!![0].size
        return Location(row, colum)
    }
}
