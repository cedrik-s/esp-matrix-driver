package com.espmatrixserver.espmatrixserver.client

import com.crazzyghost.alphavantage.AlphaVantage
import com.crazzyghost.alphavantage.Config
import com.crazzyghost.alphavantage.parameters.Interval
import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
// AlphaVantage API client using https://github.com/crazzyghost/alphavantage-java
@Service
class AlphaVantageClient {
    @Value("\${esp-matrix-server.RapidAPI.api-key:null}")
    val apiKey: String? = null
    var cfg:Config? = null

    fun init(){
        if(cfg==null){
            val cfg: Config= Config.builder()
                .key(apiKey)
                .timeOut(10)
                .build()
            AlphaVantage.api().init(cfg)
        }
    }
    fun getTimeSeries(name: String): TimeSeriesResponse? {
        init()
        val result = AlphaVantage.api().timeSeries().intraday().interval(Interval.THIRTY_MIN).forSymbol(name).fetchSync()
        return result
    }

}