package com.softserve.game_of_life.repository

import com.softserve.game_of_life.modele.Ocean
import org.springframework.stereotype.Component

@Component
class OceanRepository(
) {

    var oceans: MutableList<Ocean> = mutableListOf()

    fun addOcean(ocean: Ocean) {
        oceans.add(ocean)
    }
}
