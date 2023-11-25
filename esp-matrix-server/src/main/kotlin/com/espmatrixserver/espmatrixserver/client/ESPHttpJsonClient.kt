package com.espmatrixserver.espmatrixserver.client

import com.espmatrixserver.espmatrixserver.dto.BitmapDTO
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class ESPHttpJsonClient(val espConfiguration: ESPConfiguration) {
    val restClient = RestClient.create("http://"+espConfiguration.ip + ":" + espConfiguration.port + "/test");
    fun sentBitmap(bitmap: BitmapDTO) {
        var response = restClient.post()
            .body(bitmap)
            .retrieve()
            .toEntity(String::class.java);
    }
}