package com.espmatrixserver.espmatrixserver.DisplayingModules

import com.espmatrixserver.espmatrixserver.service.DisplayingModule

class TestDisplayModule : DisplayingModule {
    override fun loop() {
        println("TestDisplayModule")
    }
}