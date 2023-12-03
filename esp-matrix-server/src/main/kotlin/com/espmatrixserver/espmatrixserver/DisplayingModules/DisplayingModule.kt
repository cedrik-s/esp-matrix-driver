package com.espmatrixserver.espmatrixserver.DisplayingModules

import com.espmatrixserver.espmatrixserver.dto.DrawDTO

interface DisplayingModule {
    fun generateFrame(): List<DrawDTO>
    fun getDelay(): Long
}
