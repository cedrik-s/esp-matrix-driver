package com.espmatrixserver.espmatrixserver.client

import com.espmatrixserver.espmatrixserver.dto.BitmapDTO
import com.espmatrixserver.espmatrixserver.dto.drawDTO
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class ESPHttpJsonClient(espConfiguration: ESPConfiguration) {
    val baseUrl = "http://" + espConfiguration.ip + ":" + espConfiguration.port
    val restClient = RestClient.create()
    fun drawBitMap(bitmap: BitmapDTO) {
        var response = restClient.post()
            .uri("$baseUrl/bitmap")
            .body(bitmap)
            .retrieve()
            .toEntity(String::class.java)

    }
    fun drawLine(x0: Short, y0: Short, x1: Short, y1: Short, color: UShort) {
        var response = restClient.post()
            .uri("$baseUrl/drawLine")
            .body(drawDTO(x0, y0, x1, y1, color))
            .retrieve()
            .toEntity(String::class.java)
    }
}