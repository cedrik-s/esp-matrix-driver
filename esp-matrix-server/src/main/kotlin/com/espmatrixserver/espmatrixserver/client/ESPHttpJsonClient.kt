package com.espmatrixserver.espmatrixserver.client

import com.espmatrixserver.espmatrixserver.dto.BitmapDTO
import com.espmatrixserver.espmatrixserver.dto.BrightnessDTO
import com.espmatrixserver.espmatrixserver.dto.DrawDTO
import com.espmatrixserver.espmatrixserver.persistence.entity.Esp
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service


@Service
class ESPHttpJsonClient() : ESPClient{

private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

val client = OkHttpClient.Builder()
    .protocols(listOf(Protocol.HTTP_1_1))
    .build()

val gson = Gson()
fun generateBaseUrl(esp: Esp, path: String): String {
    return "http://${esp.ipAddress}:${esp.port}$path"
}

override fun drawBitMap(bitmap: BitmapDTO, esp: Esp) {
    val reqBody = gson.toJson(bitmap).toRequestBody("application/json".toMediaType())
    val response = client.newCall(
        Request.Builder()
            .url(generateBaseUrl(esp, "/bitmap"))
            .post(reqBody)
            .build()
    ).execute()

    logger.info(response.toString())
}

override fun drawLine(x1: Short, y1: Short, x2: Short, y2: Short, color: UShort, esp: Esp) {
    val reqBody = gson.toJson(DrawDTO(x1, y1, x2, y2, color,"drawLine")).toRequestBody("application/json".toMediaType())
    val response = client.newCall(
        Request.Builder()
            .url(generateBaseUrl(esp, "/line"))
            .post(reqBody)
            .build()
    ).execute()

    logger.info(response.toString())
}

override fun setBrightness(brightness: UShort, esp: Esp) {
    val reqBody = gson.toJson(BrightnessDTO(brightness)).toRequestBody("application/json".toMediaType())
    val response = client.newCall(
        Request.Builder()
            .url(generateBaseUrl(esp, "/brightness"))
            .post(reqBody)
            .build()
    ).execute()

    logger.info(response.toString())
}
}
