package com.espmatrixserver.espmatrixserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EspMatrixServerApplication

fun main(args: Array<String>) {
	runApplication<EspMatrixServerApplication>(*args)
}
