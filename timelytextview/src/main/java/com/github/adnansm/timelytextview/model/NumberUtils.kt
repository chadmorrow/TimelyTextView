package com.github.adnansm.timelytextview.model

import com.github.adnansm.timelytextview.model.number.*
import java.security.InvalidParameterException

fun getControlPointsFor(start: Int): Array<FloatArray>? {
        return when (start) {
            -1 -> Null.instance.controlPoints
            0 -> Zero.instance.controlPoints
            1 -> One.instance.controlPoints
            2 -> Two.instance.controlPoints
            3 -> Three.instance.controlPoints
            4 -> Four.instance.controlPoints
            5 -> Five.instance.controlPoints
            6 -> Six.instance.controlPoints
            7 -> Seven.instance.controlPoints
            8 -> Eight.instance.controlPoints
            9 -> Nine.instance.controlPoints
            else -> throw InvalidParameterException("Unsupported number requested")
        }
    }
