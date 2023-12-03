package com.espmatrixserver.espmatrixserver.service

import com.espmatrixserver.espmatrixserver.dto.DrawDTO

interface DisplayingModule {
    fun generateFrame(): List<DrawDTO>
}
