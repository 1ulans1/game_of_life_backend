package com.softserve.game_of_life.modele

data class Ocean(var ocean: Array<Array<Cell>>? = null) {

    fun toOceanDTO(): OceanDTO {
        var predator = 0
        var prey = 0
        val oceanForDto = ocean!!
            .map {
                it.map { cell ->
                    when (cell) {
                        is Predator -> {
                            predator++
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Ocean

        if (ocean != null) {
            if (other.ocean == null) return false
            if (!ocean.contentDeepEquals(other.ocean)) return false
        } else if (other.ocean != null) return false

        return true
    }

    override fun hashCode(): Int {
        return ocean?.contentDeepHashCode() ?: 0
    }
}
