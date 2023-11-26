package com.espmatrixserver.espmatrixserver.persistence.DataService

import com.espmatrixserver.espmatrixserver.dto.EspRegisteringDTO
import com.espmatrixserver.espmatrixserver.persistence.Repository.EspRepository
import com.espmatrixserver.espmatrixserver.persistence.entity.Esp
import com.espmatrixserver.espmatrixserver.persistence.entity.User
import org.springframework.stereotype.Service

@Service
class EspDataService(val espRepository: EspRepository, val userDataService: UserDataService) {
    fun createEsp(registeringDTO: EspRegisteringDTO): Esp {
        return this.createEsp(
            registeringDTO.macAddress,
            registeringDTO.ipAddress,
            registeringDTO.port,
            registeringDTO.height,
            registeringDTO.width
        )
    }

    fun createEsp(macAddress: String, ipAddress: String, port: Int, height: Short, width: Short): Esp {
        return this.createEsp(macAddress, ipAddress, port, height, width, userDataService.loadDefaultUser())
    }

    fun createEsp(macAddress: String, ipAddress: String, port: Int, height: Short, width: Short, owner: User): Esp {
        return espRepository.save(Esp(macAddress, ipAddress, port, height, width, owner))
    }

    fun loadEsp(macAddress: String): Esp? {
        return espRepository.getByMacAddress(macAddress)
    }

    fun updateEsp(esp: Esp): Esp {
        return espRepository.save(esp)
    }

    fun loadAllEsp(): List<Esp> {
        return espRepository.findAll()
    }

    fun loadFirstEsp(): Esp {
        return espRepository.findAll().first()
    }

}