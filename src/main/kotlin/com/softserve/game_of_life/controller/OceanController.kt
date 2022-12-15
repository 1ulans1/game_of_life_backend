package com.softserve.game_of_life.controller

import com.softserve.game_of_life.modele.Ocean
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
    fun getOcean() {
        return service.initOcean()
    }

    @GetMapping("/iterate")
    fun iterate(): OceanDTO {
        service.iteration(0)
        return service.oceanDto(0)
    }

    @GetMapping("/reset")
    fun reset(): OceanDTO {
        service.reset(0)
        return service.oceanDto(0)
    }

    @Bean
    fun output() {
        return service.initOcean()
    }
//        service.output(0)
//        service.iteration(0)
//        service.output(0)
//        service.iteration(0)
//        service.output(0)
//        service.iteration(0)
//        service.output(0)
//        service.iteration(0)
//    }
}
