package com.espmatrixserver.espmatrixserver.DisplayingModules

import com.espmatrixserver.espmatrixserver.SpringContext
import com.espmatrixserver.espmatrixserver.client.AlphaVantageClient
import com.espmatrixserver.espmatrixserver.dto.DrawDTO
import com.espmatrixserver.espmatrixserver.service.GenerateGraphService

class AlphaVantageDisplayingModule : DisplayingModule {
    override fun generateFrame(): List<DrawDTO> {
        val result = SpringContext.getBean(AlphaVantageClient::class.java).getTimeSeries("TSLA")
        val stockUnits = result?.stockUnits
        return if (stockUnits == null) {
            throw Exception("StockUnits are null")
        } else {
            GenerateGraphService.generateGraph(stockUnits)
        }
    }

    override fun getDelay(): Long {
        return 5000L
    }
}