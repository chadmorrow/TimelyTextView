package com.github.adnansm.timelytextview.model.core

import com.github.adnansm.timelytextview.model.number.*
import java.security.InvalidParameterException


/**
 * Model class for cubic bezier figure
 */
abstract class Figure protected constructor(controlPoints: Array<FloatArray>) {
    var pointsCount = NO_VALUE
        protected set

    //A chained sequence of points P0,P1,P2,P3/0,P1,P2,P3/0,...
    var controlPoints: Array<FloatArray>? = null
        protected set

    companion object {
        const val NO_VALUE = -1
    }

    init {
        this.controlPoints = controlPoints
        pointsCount = (controlPoints.size + 2) / 3
    }
}
