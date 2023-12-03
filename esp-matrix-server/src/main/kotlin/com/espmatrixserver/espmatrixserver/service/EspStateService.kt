package com.espmatrixserver.espmatrixserver.service

import com.espmatrixserver.espmatrixserver.DisplayingModules.TestDisplayModule
import com.espmatrixserver.espmatrixserver.dto.DrawDTO
import org.springframework.stereotype.Service

@Service
class EspStateService() {
    private var currentDisplayingModule: DisplayingModule = TestDisplayModule()
    fun swapDisplayingModule(newDisplayingModule: DisplayingModule){
        this.currentDisplayingModule = newDisplayingModule
    }
    fun generateFrame(): List<DrawDTO>{
        return currentDisplayingModule.generateFrame()
    }
    fun getDelay(): Long{
        return 2000L
    }


}