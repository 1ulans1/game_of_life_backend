package com.softserve.game_of_life.service

import com.softserve.game_of_life.modele.Cell
import com.softserve.game_of_life.modele.Obstacle
import com.softserve.game_of_life.modele.Predator
import com.softserve.game_of_life.modele.Prey
import com.softserve.game_of_life.repository.OceanRepository
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class OceanService(
    private val repository: OceanRepository
) {

    fun oceanIteration(index: Long): Array<Array<Cell>> {
        return repository.oceans[index]!!
    }

    fun createOcean(
        row: Int = 25,
        colum: Int = 70,
        numberPrey: Int = 150,
        numberPredator: Int = 25,
        numberObstacle: Int = 75,
        random: Random = Random.Default,
    ) {
        val ocean: Array<Array<Cell>> = Array(row) { Array(colum) { Cell() } }
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
            cell = ocean[tempRow][tempColum]
            if (cell !is Obstacle) {
                ocean[tempRow][tempColum] = Obstacle()
                temp--
            }
        }

        temp = numberPrey
        while (temp > 0) {
            tempRow = random.nextInt(row)
            tempColum = random.nextInt(colum)
            cell = ocean[tempRow][tempColum]
            if (cell !is Obstacle && cell !is Prey) {
                ocean[tempRow][tempColum] = Prey(0, 0)
                temp--
            }
        }

        temp = numberPredator
        while (temp > 0) {
            tempRow = random.nextInt(row)
            tempColum = random.nextInt(colum)
            cell = ocean[tempRow][tempColum]
            if (cell !is Obstacle && cell !is Prey && cell !is Predator) {
                ocean[tempRow][tempColum] = Predator()
                temp--
            }
        }

        repository.addOcean(ocean)
    }

    fun output(index: Long): String = repository.oceans[index]!!
        .joinToString("\n") {
            it.joinToString("") { call -> call.getDefaultImage() }
        }
}
