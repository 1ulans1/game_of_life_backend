package com.softserve.game_of_life.repository

import com.softserve.game_of_life.modele.Ocean
import org.springframework.stereotype.Component

@Component
class OceanRepository {

    var oceans: HashMap<Long, Ocean> = hashMapOf()
    var index = 0L

    fun addOcean(ocean: Ocean) {
        oceans[index++] = ocean
    }

    fun setOcean(i: Long, ocean: Ocean) {
        oceans[i] = ocean
    }

    fun getOcean(index: Long): Ocean? {
        return oceans[index]
    }
}
