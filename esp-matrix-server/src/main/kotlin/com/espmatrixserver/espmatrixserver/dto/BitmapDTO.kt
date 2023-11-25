package com.espmatrixserver.espmatrixserver.dto

import com.espmatrixserver.espmatrixserver.datamodel.BitmapData
import lombok.RequiredArgsConstructor

@RequiredArgsConstructor
class BitmapDTO {
    var x: UShort;
    var y: UShort;
    var color: UShort;
    var w: UShort;
    var h: UShort;
    var bitmap: String;

    constructor(bitmapData: BitmapData) {
        this.x = bitmapData.x;
        this.y = bitmapData.y;
        this.color = bitmapData.color;
        this.w = bitmapData.w;
        this.h = bitmapData.h;
        var list = ArrayList<Byte>();
//        var builder = StringBuilder();  //Debug Code
        bitmapData.bitmap.forEach { row ->
            var newByte: Int = 0;
            row.forEachIndexed { index, pixel ->
                if (((index + 1) % 8) == 0) {
                    list.add(newByte.toByte());
                    newByte = 0;
                }
//                builder.append(if (pixel) 1 else 0);  //Debug Code
                newByte = (newByte shl 1) or if (pixel) 1 else 0;
            }
        }
        bitmap = String(java.util.Base64.getEncoder().encode(list.toByteArray()));
    }
}