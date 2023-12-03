package com.espmatrixserver.espmatrixserver.service

import com.crazzyghost.alphavantage.timeseries.response.StockUnit
import com.espmatrixserver.espmatrixserver.dto.Color
import com.espmatrixserver.espmatrixserver.dto.DrawDTO

class GenerateGraphService {
    companion object {
        fun generateGraph(values: MutableList<StockUnit>): List<DrawDTO> {
            val height = 32
            val width = 32
            val xStart = 0
            val yStart = 0
            val drawDTOs = ArrayList<DrawDTO>()
            val maxValue = values.maxOf { stockUnit: StockUnit -> stockUnit.high }
            val minValue = values.minOf { stockUnit: StockUnit -> stockUnit.high }
            for (i in 0..<values.size) {
                val x = xStart + i
                val y = yStart + height - (values[i].high - minValue) / (maxValue - minValue) * height
                drawDTOs.add(DrawDTO.drawPixel(x.toShort(), y.toInt().toShort(), Color.BLUE))
            }
            return drawDTOs
        }
    }

}