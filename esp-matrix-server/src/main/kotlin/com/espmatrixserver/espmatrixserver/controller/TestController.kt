package com.espmatrixserver.espmatrixserver.controller

import com.espmatrixserver.espmatrixserver.client.ESPHttpJsonClient
import com.espmatrixserver.espmatrixserver.datamodel.BitmapData
import com.espmatrixserver.espmatrixserver.dto.BitmapDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(val espHttpJsonClient: ESPHttpJsonClient) {
    @GetMapping("/drawBitmap")
    fun drawBitMap() {
        return espHttpJsonClient.drawBitMap(BitmapDTO(BitmapData.generateFullBitMapData()))
    }
    @GetMapping("/drawLine")
    fun drawLine() {
        return espHttpJsonClient.drawLine(32,32,40,40,0x2392.toUShort())
    }

    @GetMapping("/setBrightness")
    fun setBrightness() {
        return espHttpJsonClient.setBrightness(20.toUShort())
    }

}
