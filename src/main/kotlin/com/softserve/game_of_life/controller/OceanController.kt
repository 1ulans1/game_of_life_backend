package com.softserve.game_of_life.controller

import com.softserve.game_of_life.modele.Ocean
import com.softserve.game_of_life.service.OceanService
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OceanController(private val service: OceanService) {

    @GetMapping
    fun oceanIteration(): Ocean {
        return service.oceanIteration(0)
    }

    @Bean
    fun output() {
        service.createOcean()
        service.output(0)
    }
}
