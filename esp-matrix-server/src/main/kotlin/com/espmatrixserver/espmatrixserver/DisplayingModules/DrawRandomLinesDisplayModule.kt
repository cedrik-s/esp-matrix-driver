package com.espmatrixserver.espmatrixserver.DisplayingModules

import com.espmatrixserver.espmatrixserver.client.ESPClient
import com.espmatrixserver.espmatrixserver.persistence.entity.Esp
import com.espmatrixserver.espmatrixserver.service.DisplayingModule
import kotlin.random.Random

class DrawRandomLinesDisplayModule(val espClient: ESPClient, val esp: Esp) : DisplayingModule {
    override fun loop() {

        espClient.drawLine(
            Random.nextInt(0,esp.width.toInt()).toShort(),
            Random.nextInt(0,esp.height.toInt()).toShort(),
            Random.nextInt(0,esp.width.toInt()).toShort(),
            Random.nextInt(0,esp.height.toInt()).toShort(),
            Random.nextInt().toUShort(),
            esp
        )
    }
}