package com.softserve.game_of_life.controller

import com.softserve.game_of_life.modele.OceanDTO
import com.softserve.game_of_life.service.OceanService
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ocean")
class OceanController(private val service: OceanService) {

    @GetMapping
    fun getOcean(): OceanDTO {
        return service.oceanDto()
    }

    @GetMapping("/iterate")
    fun iterate(): OceanDTO {
        service.iteration()
        return service.oceanDto()
    }

    @GetMapping("/reset")
    fun reset(): OceanDTO {
        service.reset()
        return service.oceanDto()
    }

    @Bean
    fun output() {
        return service.initOcean()
    }
}
