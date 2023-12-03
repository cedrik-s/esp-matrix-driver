package com.espmatrixserver.espmatrixserver.DisplayingModules

import com.espmatrixserver.espmatrixserver.SpringContext
import com.espmatrixserver.espmatrixserver.client.AlphaVantageClient
import com.espmatrixserver.espmatrixserver.dto.DrawDTO
import com.espmatrixserver.espmatrixserver.service.GenerateGraphService
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class AlphaVantageDisplayingModule : DisplayingModule {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun generateFrame(): List<DrawDTO> {
        val result = SpringContext.getBean(AlphaVantageClient::class.java).getTimeSeries("TSLA")
        val stockUnits = result?.stockUnits
        if(stockUnits?.size==0){
            logger.warn("StockUnits are empty")
            logger.warn(result?.errorMessage)
            return emptyList();
        }
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