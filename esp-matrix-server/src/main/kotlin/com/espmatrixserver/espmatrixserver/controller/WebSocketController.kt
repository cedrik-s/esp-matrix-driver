package com.espmatrixserver.espmatrixserver.controller

import com.espmatrixserver.espmatrixserver.dto.DrawDTO
import com.google.gson.Gson
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import kotlin.random.Random


@Controller
class WebSocketController {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    val gson = Gson()

    @MessageMapping("/drawInstructions")
    @SendTo("/client/drawInstructions")
    fun pollInstructions(message: String): String {
        Thread.sleep(500)
        logger.warn("received message: $message")
        val drawDTOs: List<DrawDTO> = ArrayList()
        for(i in 0..2){
            drawDTOs.addLast(DrawDTO(
                Random.nextInt(0,128).toShort(),
                Random.nextInt(0,64).toShort(),
                Random.nextInt(0,128).toShort(),
                Random.nextInt(0,64).toShort(),
                Random.nextInt().toUShort(),"fillRect"))
        }
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