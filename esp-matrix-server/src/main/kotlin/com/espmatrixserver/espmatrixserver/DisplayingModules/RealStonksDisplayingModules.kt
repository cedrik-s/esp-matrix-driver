package com.espmatrixserver.espmatrixserver.DisplayingModules

import com.espmatrixserver.espmatrixserver.SpringContext
import com.espmatrixserver.espmatrixserver.client.RealStonksClient
import com.espmatrixserver.espmatrixserver.dto.Color
import com.espmatrixserver.espmatrixserver.dto.DrawDTO

class RealStonksDisplayingModule: DisplayingModule {
    override fun generateFrame(): List<DrawDTO> {
        val result = SpringContext.getBean(RealStonksClient::class.java).getStockInformation("TSLA")
        return listOf(DrawDTO.drawText(result.price.toString(), Color.GREEN))
    }

    override fun getDelay(): Long {
        return 5000L
    }

}