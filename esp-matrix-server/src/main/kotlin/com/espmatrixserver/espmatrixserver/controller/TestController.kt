package com.espmatrixserver.espmatrixserver.controller

import com.espmatrixserver.espmatrixserver.client.ESPHttpJsonClient
import com.espmatrixserver.espmatrixserver.datamodel.BitmapData
import com.espmatrixserver.espmatrixserver.dto.BitmapDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(val espHttpJsonClient: ESPHttpJsonClient) {
    @GetMapping("/test")
    fun test() {
        return espHttpJsonClient.sentBitmap(BitmapDTO(BitmapData.generateFullBitMapData()))
    }
}