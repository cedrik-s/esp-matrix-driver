package com.espmatrixserver.espmatrixserver.service

import com.espmatrixserver.espmatrixserver.persistence.entity.Esp

class EspState(private var currentDisplayingModule: DisplayingModule, val esp:Esp) : Runnable {
    var isStopped: Boolean = false
        private set
    private var lastTimeStamp:Long = -1
    override fun run() {
        try {
            while(!isStopped){
                currentDisplayingModule.loop()
                lastTimeStamp = System.currentTimeMillis()
            }
        }catch(e:Exception){
            e.printStackTrace()
            stop()
        }
    }
    fun swapDisplayingModule(newDisplayingModule: DisplayingModule){
        this.currentDisplayingModule = newDisplayingModule
    }
    fun stop(){
        isStopped = true
    }


}