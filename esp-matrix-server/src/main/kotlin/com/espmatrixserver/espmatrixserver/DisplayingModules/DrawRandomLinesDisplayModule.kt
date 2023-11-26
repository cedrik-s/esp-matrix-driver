package com.espmatrixserver.espmatrixserver.DisplayingModules

import com.espmatrixserver.espmatrixserver.client.ESPHttpJsonClient
import com.espmatrixserver.espmatrixserver.persistence.entity.Esp
import com.espmatrixserver.espmatrixserver.service.DisplayingModule
import kotlin.random.Random

class DrawRandomLinesDisplayModule(val espHttpJsonClient: ESPHttpJsonClient, val esp: Esp) : DisplayingModule {
    override fun loop() {

        espHttpJsonClient.drawLine(
            (Random.nextInt() % esp.width).toShort(),
            (Random.nextInt() % esp.height).toShort(),
            (Random.nextInt() % esp.width).toShort(),
            (Random.nextInt() % esp.height).toShort(),
            Random.nextInt().toUShort(),
            esp
        )
    }
}