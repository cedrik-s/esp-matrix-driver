package com.espmatrixserver.espmatrixserver.client

import com.espmatrixserver.espmatrixserver.dto.BitmapDTO
import com.espmatrixserver.espmatrixserver.persistence.entity.Esp
import org.springframework.stereotype.Service

@Service
class ESPWebSocketClient: ESPClient {
    override fun drawLine(x1: Short, y1: Short, x2: Short, y2: Short, color: UShort, esp: Esp) {
        TODO("Not yet implemented")
    }

    override fun drawBitMap(bitmap: BitmapDTO, esp: Esp) {
        TODO("Not yet implemented")
    }

    override fun setBrightness(brightness: UShort, esp: Esp) {
        TODO("Not yet implemented")
    }
}