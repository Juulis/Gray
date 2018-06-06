package com.juulis.grays

import android.util.Log
import java.time.Instant
import java.time.ZoneId
import java.util.*
import java.time.temporal.ChronoUnit.DAYS

class Medication(startDate: Date, selectedDate: Date) {


    var bulkAmount: Int? = null
    var frequency: Int? = null
    var pilAmount: String? = null
    var fase: String? = null
    var daysProgress: Long


    private val TAG = "Testing in Medication "

    init {
        val startDateToLocal = Instant.ofEpochMilli(startDate.time).atZone(ZoneId.systemDefault()).toLocalDate()
        val selectedDateToLocal = Instant.ofEpochMilli(selectedDate.time).atZone(ZoneId.systemDefault()).toLocalDate()
        daysProgress = DAYS.between(startDateToLocal, selectedDateToLocal)
        when (daysProgress) {
            in 0..2 -> fase = "A"
            in 3..5 -> fase = "B"
            in 6..8 -> fase = "C"
            in 9..11 -> fase = "D"
            in 12..14 -> fase = "E"
            in 15..28 -> fase = "F"
            in 29..42 -> fase = "G" //TODO can be 7 days more
            in 43..63 -> fase = "H"
            in 64..84 -> fase = "I" //TODO can  be 21 days more
        }
        if (fase != null)
            setupInfo()
        else Log.d(TAG, "No fase set")
    }


    private fun setupInfo() {
        val pilAmountDouble = MedicationFactory.valueOf(fase!!).pilAmount
        pilAmount = if (pilAmountDouble % 1 == 0.0) pilAmountDouble.toInt().toString() else String.format("%.1f",pilAmountDouble)
        frequency = MedicationFactory.valueOf(fase!!).freq
        bulkAmount = MedicationFactory.valueOf(fase!!).bulkAmount
        daysProgress += 1
    }
}


enum class MedicationFactory(val bulkAmount: Int, val pilAmount: Double, val freq: Int) {
    A(1, 0.0, 1),
    B(1, 0.0, 2),
    C(2, 0.0, 1),
    D(2, 0.5, 1),
    E(2, 0.5, 2),
    F(2, 1.0, 2),
    G(2, 1.0, 3),
    H(2, 2.0, 3), //TODO can be 4 freq
    I(2, 3.0, 3)  //TODO can be 4 freq
}