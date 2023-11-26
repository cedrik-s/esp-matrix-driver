package com.espmatrixserver.espmatrixserver.controller

import com.espmatrixserver.espmatrixserver.dto.EspRegisteringDTO
import com.espmatrixserver.espmatrixserver.service.EspStateService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
@Controller
class EspRegisteringController(val espStateService: EspStateService) {
    @PostMapping("/register", consumes = ["application/json"])
    fun register(@RequestBody() espRegisteringDTO: EspRegisteringDTO) {
        espStateService.register(espRegisteringDTO)
    }


}