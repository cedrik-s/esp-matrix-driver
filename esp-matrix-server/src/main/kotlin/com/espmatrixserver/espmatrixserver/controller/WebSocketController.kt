package com.espmatrixserver.espmatrixserver.controller

import com.espmatrixserver.espmatrixserver.dto.DrawDTO
import com.espmatrixserver.espmatrixserver.service.EspStateService
import com.google.gson.Gson
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller


@Controller
class WebSocketController(val espStateService: EspStateService) {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    val gson = Gson()

    @MessageMapping("/RequestDrawInstructions")
    @SendTo("/client/drawInstructions")
    fun pollInstructions(@Header("simpSessionId") sessionId: String, message: String): String {
        Thread.sleep(espStateService.getDelay())
        return parseToJson(espStateService.generateFrame())
    }


    fun parseToJson(drawDTOs: List<DrawDTO>): String {
        StringBuilder().apply {
            drawDTOs.forEach {
                append(gson.toJson(it))
                append(";;")
            }
        }.toString().also {
            logger.warn("sending message: $it")
            return it
        }
    }
}