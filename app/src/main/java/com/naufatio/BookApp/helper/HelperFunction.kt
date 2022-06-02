package com.naufatio.BookApp.helper

import android.graphics.Color
import java.util.*

object HelperFunction {

    fun generateRandomColor(): Int{
        val random = Random()
        val red = random.nextInt(255)
        val green = random.nextInt(255)
        val blue = random.nextInt(255)
        return -0x1000000 or (red shl 16) or (green shl 8) or blue
    }

    fun generateRandomPastelColor(): Int {
        val r = Random()
        val s = r.nextInt(256)
        val v = r.nextInt(256)
        return Color.rgb(s, v, 255)
    }

}