package com.espmatrixserver.espmatrixserver.dto

import com.google.gson.annotations.SerializedName

enum class DrawMethod() {
    @SerializedName("drawLine")
    LINE,

    @SerializedName("drawRect")
    RECT,

    @SerializedName("fillRect")
    FILLRECT,

    @SerializedName("fillScreen")
    FILLSCREEN,

    @SerializedName("clearScreen")
    CLEARSCREEN,

    @SerializedName("fillCircle")
    FILLCIRCLE,

    @SerializedName("drawCircle")
    DRAWCIRCLE,

    @SerializedName("setBrightness")
    BRIGHTNESS,

    @SerializedName("drawText")
    DRAWTEXT,
}