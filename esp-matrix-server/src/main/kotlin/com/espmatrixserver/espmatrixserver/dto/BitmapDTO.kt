package com.espmatrixserver.espmatrixserver.dto

import com.espmatrixserver.espmatrixserver.datamodel.BitmapData
import lombok.RequiredArgsConstructor

@Suppress("MemberVisibilityCanBePrivate")
@RequiredArgsConstructor
class BitmapDTO//                builder.append(if (pixel) 1 else 0)  //Debug Code//        var builder = StringBuilder()  //Debug Code
    (bitmapData: BitmapData) {
    val x: UShort
    val y: UShort
    val color: UShort
    val w: UShort
    val h: UShort
    val bitmap: String

    init {
        this.x = bitmapData.x
        this.y = bitmapData.y
        this.color = bitmapData.color
        this.w = bitmapData.w
        this.h = bitmapData.h
        val list = ArrayList<Byte>()
        var builder = StringBuilder()
        bitmapData.bitmap.forEach { row ->
            var newByte: Int = 0
            row.forEachIndexed { index: Int, pixel: Boolean ->
                builder.append(if (pixel) 1 else 0)
                newByte = (newByte shl 1) or if (pixel) 1 else 0
                if (((index + 1) % 8) == 0) {
                    list.add(newByte.toByte())
                    newByte = 0
                }

            }
        }
        bitmap = String(java.util.Base64.getEncoder().encode(list.toByteArray()))
    }
}