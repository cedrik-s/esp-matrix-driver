package com.espmatrixserver.espmatrixserver.DisplayingModules

import com.espmatrixserver.espmatrixserver.dto.Color
import com.espmatrixserver.espmatrixserver.dto.DrawDTO
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TestDisplayModule() : DisplayingModule {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun generateFrame(): List<DrawDTO> {
        val drawDTOs: List<DrawDTO> = ArrayList()
        drawDTOs.addLast(
            DrawDTO.drawText(0, 0, "This is a Test Message", Color.RED,1u)
        )
        drawDTOs.addLast(
            DrawDTO.drawText(0, 9, "This is a Test Message", Color.GREEN,2u)
        )
        drawDTOs.addLast(
            DrawDTO.drawText(0, 25, "This is a Test Message", Color.BLUE,3u)
        )
        drawDTOs.addLast(
            DrawDTO.setBrightness(40u)
        )
        return drawDTOs
    }

    override fun getDelay(): Long {
        return 1000L
    }
}