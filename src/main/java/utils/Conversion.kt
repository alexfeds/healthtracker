package utils

object Conversion {
    fun round(numberToConvert: Double, precision: Double): Double {
        val p = Math.pow(10.0, precision)
        return Math.round(numberToConvert * p).toDouble() / p
    }

    fun convertKGtoPounds(numberToConvert: Double, precision: Double): Double {
        return round(numberToConvert * 2.2, precision)
    }

    fun convertMetresToInches(numberToConvert: Double, precision: Double): Double {
        return round(numberToConvert * 39.37, precision)
    }
}