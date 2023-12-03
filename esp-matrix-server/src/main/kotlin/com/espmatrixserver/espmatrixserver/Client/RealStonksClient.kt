package com.espmatrixserver.espmatrixserver.Client

import com.espmatrixserver.espmatrixserver.dto.RealStonksDTO
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

// Client for the API https://rapidapi.com/amansharma2910/api/realstonks
@Service
class RealStonksClient {
    @Value("\${esp-matrix-server.RealStonks.api-key:null}")
    val apiKey: String? = null

    val httpClient = RestClient.create()

    fun getStockInformation(name: String): RealStonksDTO {
        val result = httpClient.get().uri("https://realstonks.p.rapidapi.com/$name").headers { headers ->
            headers.set("x-rapidapi-key", apiKey)
            headers.set("x-rapidapi-host", "realstonks.p.rapidapi.com")
        }.retrieve().toEntity(RealStonksDTO::class.java)
        return result.body ?: throw Exception("RealStonks API returned null")

    }

}