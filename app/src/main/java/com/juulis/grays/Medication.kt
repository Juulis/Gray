package com.juulis.grays

import android.util.Log
import java.time.Instant
import java.time.ZoneId
import java.util.*
import java.time.temporal.ChronoUnit.DAYS

class Medication (startDate: Date, selectedDate: Date) {


    var bulkAmount: Int? = null
    var bulkFreq: Int? = null
    var pilAmount: Number? = null
    var fase: String? = null


    private val TAG = "Testing in Medication "

    init {
        val startDateToLocal = Instant.ofEpochMilli(startDate.time).atZone(ZoneId.systemDefault()).toLocalDate()
        val selectedDateToLocal = Instant.ofEpochMilli(selectedDate.time).atZone(ZoneId.systemDefault()).toLocalDate()
        val daysPassed = DAYS.between(startDateToLocal,selectedDateToLocal)
        when (daysPassed) {
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
        bulkAmount = MedicationFactory.valueOf(fase!!).bulkAmount
        bulkFreq = MedicationFactory.valueOf(fase!!).freq
        pilAmount = MedicationFactory.valueOf(fase!!).pilAmount
    }


}


enum class MedicationFactory(val bulkAmount: Int, val pilAmount: Number, val freq: Int) {
    A(1, 0, 1),
    B(1, 0, 2),
    C(2, 0, 1),
    D(2, 0.5, 1),
    E(2, 0.5, 2),
    F(2, 1, 2),
    G(2, 1, 3),
    H(2, 2, 3), //TODO can be 4 freq
    I(2, 3, 3)  //TODO can be 4 freq

}