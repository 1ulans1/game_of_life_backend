package com.softserve.game_of_life

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GameOfLifeApplication

fun main(args: Array<String>) {
    runApplication<GameOfLifeApplication>(*args)
}
