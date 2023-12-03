package com.espmatrixserver.espmatrixserver.dto


class Color(val r: Short, val g: Short, val b: Short) {
    init {
        if (r > 255 || r < 0) throw IllegalArgumentException("r must be between 0 and 255")
        if (g > 255 || g < 0) throw IllegalArgumentException("g must be between 0 and 255")
        if (b > 255 || b < 0) throw IllegalArgumentException("b must be between 0 and 255")
    }
    constructor() : this(0, 0, 0)
    companion object {
        val BLACK = Color(0, 0, 0)
        val WHITE = Color(255, 255, 255)
        val RED = Color(255, 0, 0)
        val GREEN = Color(0, 255, 0)
        val BLUE = Color(0, 0, 255)
        val YELLOW = Color(255, 255, 0)
        val CYAN = Color(0, 255, 255)
        val MAGENTA = Color(255, 0, 255)
    }
}