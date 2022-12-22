package com.softserve.game_of_life.modele

class Obstacle(ocean: Ocean, location: Location) : Cell(ocean, location) {
    override fun getDefaultImage() = "⬛"
    override fun process() {}
    override fun createNewCell(location: Location) = Obstacle(ocean, location)
}
