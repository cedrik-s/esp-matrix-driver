package com.espmatrixserver.espmatrixserver.service

import com.espmatrixserver.espmatrixserver.DisplayingModules.DrawRandomLinesDisplayModule
import com.espmatrixserver.espmatrixserver.client.ESPHttpJsonClient
import com.espmatrixserver.espmatrixserver.dto.EspRegisteringDTO
import com.espmatrixserver.espmatrixserver.persistence.DataService.EspDataService
import com.espmatrixserver.espmatrixserver.persistence.entity.Esp
import org.springframework.stereotype.Service

@Service
class EspStateService(val espDataService: EspDataService,val espHttpJsonClient: ESPHttpJsonClient) {
    var espStatesByMac: MutableMap<String, EspState> = mutableMapOf()

    fun register(registeringDTO: EspRegisteringDTO): Esp {
        var esp = espDataService.loadEsp(registeringDTO.macAddress)
        if (esp != null) {
            esp.ipAddress = registeringDTO.ipAddress
            esp.height = registeringDTO.height
            esp.width = registeringDTO.width
            esp.port = registeringDTO.port
            espDataService.updateEsp(esp)
        } else {
            esp = espDataService.createEsp(registeringDTO)
        }
        val state= espStatesByMac[esp.macAddress]
        if(state == null) {
            startNewStateThread(esp)
        }else{
            if(state.isStopped){
                startNewStateThread(esp)
            }
        }
        return esp
    }
    fun startNewStateThread(esp: Esp) {
        espStatesByMac[esp.macAddress] = EspState(DrawRandomLinesDisplayModule(espHttpJsonClient,esp),esp)
        Thread(espStatesByMac[esp.macAddress]).start()
    }


}