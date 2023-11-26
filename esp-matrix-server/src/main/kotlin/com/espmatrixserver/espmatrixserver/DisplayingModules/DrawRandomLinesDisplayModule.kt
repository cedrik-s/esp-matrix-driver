package com.espmatrixserver.espmatrixserver.DisplayingModules

import com.espmatrixserver.espmatrixserver.client.ESPHttpJsonClient
import com.espmatrixserver.espmatrixserver.persistence.entity.Esp
import com.espmatrixserver.espmatrixserver.service.DisplayingModule
import kotlin.random.Random

class DrawRandomLinesDisplayModule(val espHttpJsonClient: ESPHttpJsonClient, val esp: Esp) : DisplayingModule {
    override fun loop() {

        espHttpJsonClient.drawLine(
            Random.nextInt(0,esp.width.toInt()).toShort(),
            Random.nextInt(0,esp.height.toInt()).toShort(),
            Random.nextInt(0,esp.width.toInt()).toShort(),
            Random.nextInt(0,esp.height.toInt()).toShort(),
            Random.nextInt().toUShort(),
            esp
        )
    }
}