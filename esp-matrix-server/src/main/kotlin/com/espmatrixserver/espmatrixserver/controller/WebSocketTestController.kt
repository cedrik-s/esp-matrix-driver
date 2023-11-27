package com.espmatrixserver.espmatrixserver.controller

import com.espmatrixserver.espmatrixserver.dto.drawDTO
import com.google.gson.Gson
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import kotlin.random.Random


@Controller
class WebSocketTestController {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    val gson = Gson()
    @MessageMapping("/hello")
    @SendTo("/topic/test")
    fun greeting(message: String): String {
        logger.warn("received message: $message")
                return gson.toJson(
            drawDTO(
                Random.nextInt(0, 128).toShort(),
                Random.nextInt(0, 64).toShort(),
                Random.nextInt(0, 128).toShort(),
                Random.nextInt(0, 64).toShort(),
                Random.nextInt().toUShort()
            )
        )
    }
}