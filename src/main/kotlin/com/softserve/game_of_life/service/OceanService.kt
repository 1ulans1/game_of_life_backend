package com.softserve.game_of_life.service

import com.softserve.game_of_life.modele.*
import com.softserve.game_of_life.repository.OceanRepository
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import kotlin.random.Random

@Service
class OceanService(
    private val repository: OceanRepository
) {

    fun getOcean(index: Long): Ocean {
        return repository.getOcean(index) ?: throw RuntimeException()
    }

    fun createOcean(
        row: Int = 25,
        colum: Int = 70,
        numberPrey: Int = 150,
        numberPredator: Int = 25,
        numberObstacle: Int = 75,
        random: Random = Random.Default,
    ): Ocean {
        val ocean: Ocean = Ocean(Array(row) { Array(colum) { Empty.instance } })

        if (row < 1 || colum < 1 || numberPrey < 0 || numberPredator < 0 || numberObstacle < 0) {
            throw IllegalArgumentException()
        }

        var cell: Cell
        var temp = numberObstacle
        var tempRow: Int
        var tempColum: Int

        while (temp > 0) {
            tempRow = random.nextInt(row)
            tempColum = random.nextInt(colum)
            cell = ocean.ocean[tempRow][tempColum]
            if (cell !is Obstacle) {
                ocean.ocean[tempRow][tempColum] = Obstacle()
                temp--
            }
        }

        temp = numberPrey
        while (temp > 0) {
            tempRow = random.nextInt(row)
            tempColum = random.nextInt(colum)
            cell = ocean.ocean[tempRow][tempColum]
            if (cell !is Obstacle && cell !is Prey) {
                ocean.ocean[tempRow][tempColum] = Prey(
                    ocean = ocean,
                    location = Location(tempRow, tempColum)
                )
                temp--
            }
        }

        temp = numberPredator
        while (temp > 0) {
            tempRow = random.nextInt(row)
            tempColum = random.nextInt(colum)
            cell = ocean.ocean[tempRow][tempColum]
            if (cell !is Obstacle && cell !is Prey && cell !is Predator) {
                ocean.ocean[tempRow][tempColum] = Predator(ocean = ocean, location = Location(tempRow, tempColum))
                temp--
            }
        }

        return ocean
    }

    fun initOcean() {
        repository.addOcean(createOcean())
    }

    fun output(index: Long) =
        getOcean(index).ocean
            .joinToString("\n") {
                it.joinToString("") { call -> call.getDefaultImage() }
            } + "\n"

    fun iteration(index: Long) {
        val ocean = getOcean(index).ocean
        ocean.forEach { calls ->
            calls.forEach { call ->
                call.process()
            }
        }
    }

    fun oceanDto(i: Long): OceanDTO {

        return repository.getOcean(i)!!.toOceanDTO()
    }

    fun reset(i: Int) {
        repository.setOcean(0, createOcean())
    }
}
