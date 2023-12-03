package com.espmatrixserver.espmatrixserver.dto

class DrawDTO private constructor (val xStart: Short,
               val yStart: Short,
               val xEnd: Short,
               val yEnd: Short,
               val colorR: Short,
               val colorG: Short,
               val colorB: Short,
               val method: DrawMethod,
               val radius: UShort,
               val size: UShort,
               val stringParam: String,
               val intParam: UShort){
    private constructor(xStart: Short,yStart: Short,xEnd: Short,yEnd: Short,color: Color,method: DrawMethod,radius: UShort,size: UShort,stringParam: String,intParam: UShort):
            this(xStart,yStart,xEnd,yEnd,color.r,color.g,color.b,method,radius,size,stringParam,intParam)
    companion object {
        fun drawLine(xStart: Short,yStart: Short,xEnd: Short,yEnd: Short,color: Color): DrawDTO  {
            return DrawDTO(xStart,yStart,xEnd,yEnd,color,DrawMethod.LINE,0u,0u,"",0u)
        }
        fun drawRect(xStart: Short,yStart: Short,xEnd: Short,yEnd: Short,color: Color): DrawDTO  {
            return DrawDTO(xStart,yStart,xEnd,yEnd,color,DrawMethod.RECT,0u,0u,"",0u)
        }
        fun fillRect(xStart: Short,yStart: Short,xEnd: Short,yEnd: Short,color: Color): DrawDTO  {
            return DrawDTO(xStart,yStart,xEnd,yEnd,color,DrawMethod.FILLRECT,0u,0u,"",0u)
        }
        fun fillScreen(color: Color): DrawDTO  {
            return DrawDTO(0,0,0,0,color,DrawMethod.FILLSCREEN,0u,0u,"",0u)
        }
        fun clearScreen(): DrawDTO  {
            return DrawDTO(0,0,0,0,Color(),DrawMethod.CLEARSCREEN,0u,0u,"",0u)
        }
        fun fillCircle(xStart: Short,yStart: Short,radius: UShort,color: Color): DrawDTO  {
            return DrawDTO(xStart,yStart,0,0,color,DrawMethod.FILLCIRCLE,radius,0u,"",0u)
        }
        fun drawCircle(xStart: Short,yStart: Short,radius: UShort,color: Color): DrawDTO  {
            return DrawDTO(xStart,yStart,0,0,color,DrawMethod.DRAWCIRCLE,radius,0u,"",0u)
        }
        fun setBrightness(brightness:UShort): DrawDTO  {
            var correctedBrightness = brightness
            if(correctedBrightness>255u) correctedBrightness=255u
            else if(correctedBrightness<0u) correctedBrightness=0u
            return DrawDTO(0,0,0,0,Color(),DrawMethod.BRIGHTNESS,0u,0u,"",correctedBrightness)
        }
        fun drawText(xStart: Short,yStart: Short,text:String,color: Color,size: UShort): DrawDTO  {
            return DrawDTO(xStart,yStart,0,0,color,DrawMethod.DRAWTEXT,0u,size,text,0u)
        }
        fun drawText(xStart: Short,yStart: Short,text:String,color: Color): DrawDTO  {
            return DrawDTO(xStart,yStart,0,0,color,DrawMethod.DRAWTEXT,0u,1u,text,0u)
        }
        fun drawText(text:String,color: Color): DrawDTO  {
            return DrawDTO(0,0,0,0,color,DrawMethod.DRAWTEXT,0u,1u,text,0u)
        }
    }
}