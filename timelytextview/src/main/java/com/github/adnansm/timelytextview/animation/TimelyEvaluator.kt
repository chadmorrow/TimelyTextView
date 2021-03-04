package com.github.adnansm.timelytextview.animation

import com.nineoldandroids.animation.TypeEvaluator

class TimelyEvaluator : TypeEvaluator<Array<FloatArray>?> {
    private var cachedPoints: Array<FloatArray>? = null
    override fun evaluate(
        fraction: Float,
        startValue: Array<FloatArray>?,
        endValue: Array<FloatArray>?
    ): Array<FloatArray>? {
        val pointsCount = startValue!!.size
        initCache(pointsCount)
        for (i in 0 until pointsCount) {
            cachedPoints!![i][0] =
                startValue[i][0] + fraction * (endValue!![i][0] - startValue[i][0])
            cachedPoints!![i][1] =
                startValue[i][1] + fraction * (endValue[i][1] - startValue[i][1])
        }
        return cachedPoints
    }

    private fun initCache(pointsCount: Int) {
        if (cachedPoints == null || cachedPoints!!.size != pointsCount) {
            cachedPoints = Array(pointsCount) { FloatArray(2) }
        }
    }
}