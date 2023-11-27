package com.espmatrixserver.espmatrixserver.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller


@Controller
class WebSocketTestController {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @MessageMapping("/hello")
    @SendTo("/topic/test")
    fun greeting(message: String): String {
        Thread.sleep(1000) // simulated delay
        logger.warn("received message: $message")
        return "Test Message $message"
    }
}