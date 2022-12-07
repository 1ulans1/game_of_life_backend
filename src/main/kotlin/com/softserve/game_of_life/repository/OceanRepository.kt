package com.softserve.game_of_life.repository

import com.softserve.game_of_life.modele.Cell
import org.springframework.stereotype.Component

@Component
class OceanRepository(
) {

    var oceans: HashMap<Long, Array<Array<Cell>>> = hashMapOf()

    var index: Long = 0

    fun addOcean(ocean: Array<Array<Cell>>) {
        oceans[index++] = ocean
    }

}
