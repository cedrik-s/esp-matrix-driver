package com.espmatrixserver.espmatrixserver.client

import com.espmatrixserver.espmatrixserver.dto.BitmapDTO
import com.espmatrixserver.espmatrixserver.dto.drawDTO
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service


@Service
class ESPHttpJsonClient(espConfiguration: ESPConfiguration) {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    val baseUrl = "http://" + espConfiguration.ip + ":" + espConfiguration.port

    val client = OkHttpClient.Builder()
        .protocols(listOf(Protocol.HTTP_1_1))
        .build();

    val gson = Gson();

    fun drawBitMap(bitmap: BitmapDTO) {
        val reqBody = gson.toJson(bitmap).toRequestBody("application/json".toMediaType())
        var response = client.newCall(
            Request.Builder()
                .url("$baseUrl/bitmap")
                .post(reqBody)
                .build()
        ).execute()

        logger.info(response.toString());
    }
    fun drawLine(x0: Short, y0: Short, x1: Short, y1: Short, color: UShort) {
        val reqBody = gson.toJson(drawDTO(x0, y0, x1, y1, color)).toRequestBody("application/json".toMediaType())
        var response = client.newCall(
            Request.Builder()
                .url("$baseUrl/line")
                .post(reqBody)
                .build()
        ).execute()

        logger.info(response.toString());
    }
}
