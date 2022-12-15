package com.softserve.game_of_life.modele

data class Ocean(val ocean: Array<Array<Cell>>) {

    fun toOceanDTO(): OceanDTO {
        var predator = 0
        var prey = 0
        val oceanForDto = ocean
            .map {
                it.map { cell ->
                    when (cell) {
                        is Predator -> {
                            predator++;
                            1
                        }

                        is Prey -> {
                            prey++
                            2
                        }

                        is Obstacle -> {
                            3
                        }

                        else -> {
                            0
                        }
                    }
                }
            }
        return OceanDTO(
            predator,
            prey,
            oceanForDto
        )
    }
}
