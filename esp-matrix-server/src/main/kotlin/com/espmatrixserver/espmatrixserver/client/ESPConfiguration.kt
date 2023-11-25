package com.espmatrixserver.espmatrixserver.client

import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
@RequiredArgsConstructor
class ESPConfiguration {
    @Value("\${esp-matrix.esp.ip}")
    var ip: String? = "test"
    @Value("\${esp-matrix.esp.port}")
    var port: Int? = null

}