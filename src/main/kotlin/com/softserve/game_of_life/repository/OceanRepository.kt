package com.softserve.game_of_life.repository

import com.softserve.game_of_life.modele.Ocean
import org.springframework.stereotype.Component

@Component
class OceanRepository {

    lateinit var oceans: Ocean

    fun addOcean(ocean: Ocean) {
        oceans = ocean
    }

    fun setOcean(ocean: Ocean) {
        oceans = ocean
    }

    fun getOcean(): Ocean {
        return oceans
    }
}
