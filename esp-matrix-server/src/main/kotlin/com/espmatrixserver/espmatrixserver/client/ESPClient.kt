package com.espmatrixserver.espmatrixserver.client

import com.espmatrixserver.espmatrixserver.dto.BitmapDTO
import com.espmatrixserver.espmatrixserver.persistence.entity.Esp

interface ESPClient {
    fun drawLine(x1: Short, y1: Short, x2: Short, y2: Short, color: UShort, esp: Esp)
    fun drawBitMap(bitmap: BitmapDTO, esp: Esp)
    fun setBrightness(brightness: UShort, esp: Esp)

}