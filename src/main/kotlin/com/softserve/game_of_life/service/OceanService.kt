package com.softserve.game_of_life.service

import com.softserve.game_of_life.modele.*
import com.softserve.game_of_life.repository.OceanRepository
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class OceanService(
    private val repository: OceanRepository
) {

    fun getOcean(): Ocean {
        return repository.getOcean()
    }

    fun createOcean(
        row: Int = 25,
        colum: Int = 70,
        numberPrey: Int = 150,
        numberPredator: Int = 25,
        numberObstacle: Int = 75,
        random: Random = Random.Default,
    ): Ocean {
        val ocean = Ocean()

        ocean.ocean = Array(row) { currentRow ->
            Array(colum) { currentColum ->
                Empty(
                    ocean,
                    Location(currentRow, currentColum)
                )
            }
        }
        if (row < 1 || colum < 1 || numberPrey < 0 || numberPredator < 0 || numberObstacle < 0) {
            throw IllegalArgumentException()
        }

        val cells = List(row * colum) { Location(it / colum, it % colum) }
        val shuffledCells = cells.shuffled(random)

        var temp = numberObstacle
        var i = 0
        while (temp > 0) {
            val cell = ocean.ocean!![shuffledCells[i].row][shuffledCells[i].colum]
            if (cell !is Obstacle) {
                ocean.ocean!![shuffledCells[i].row][shuffledCells[i].colum] = Obstacle(
                    ocean = ocean,
                    location = Location(shuffledCells[i].row, shuffledCells[i].colum)
                )
                temp--
            }
            i++
        }

        temp = numberPrey
        while (temp > 0) {
            val cell = ocean.ocean!![shuffledCells[i].row][shuffledCells[i].colum]
            if (cell !is Obstacle && cell !is Prey) {
                ocean.ocean!![shuffledCells[i].row][shuffledCells[i].colum] = Prey(
                    ocean = ocean,
                    location = Location(shuffledCells[i].row, shuffledCells[i].colum)
                )
                temp--
            }
            i++
        }

        temp = numberPredator
        while (temp > 0) {
            val cell = ocean.ocean!![shuffledCells[i].row][shuffledCells[i].colum]
            if (cell !is Obstacle && cell !is Prey && cell !is Predator) {
                ocean.ocean!![shuffledCells[i].row][shuffledCells[i].colum] = Predator(
                    ocean = ocean,
                    location = Location(shuffledCells[i].row, shuffledCells[i].colum)
                )
                temp--
            }
            i++
        }

        return ocean
    }

    fun initOcean() {
        repository.addOcean(createOcean())
    }

    fun output() =
        getOcean().ocean!!
            .joinToString("\n") {
                it.joinToString("") { call -> call.getDefaultImage() }
            } + "\n"

    fun iteration() {
        val ocean = getOcean().ocean
        val processedCells = mutableSetOf<Cell>()

        ocean!!.forEach { calls ->
            calls.forEach { call ->
                if (call !in processedCells) {
                    call.process()
                    processedCells.add(call)
                }
            }
        }
    }

    fun oceanDto(): OceanDTO {
        return repository.getOcean().toOceanDTO()
    }

    fun reset() {
        repository.setOcean(createOcean())
    }
}
