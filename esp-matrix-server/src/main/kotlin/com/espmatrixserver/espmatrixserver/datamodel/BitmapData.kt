package com.espmatrixserver.espmatrixserver.datamodel

import lombok.RequiredArgsConstructor

@RequiredArgsConstructor
class BitmapData(
    var x: UShort,
    var y: UShort,
    var color: UShort,
    var w: UShort,
    var h: UShort,
    var bitmap: Array<Array<Boolean>>
) {
    companion object {
        fun generateFullBitMapData(): BitmapData {

            val bitmap = Array(64) { Array(128) { false } }

            bitmap.forEachIndexed{ indexRow, row ->
                row[0] = true
                row[row.size-1] = true
                row[row.size/2] = true
            }
            return BitmapData(0u, 0u,             1200u
                , 128u, 64u, bitmap)
        }
    }
}