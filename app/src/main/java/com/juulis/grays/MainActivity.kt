package com.juulis.grays

import android.app.Activity
import android.app.AlertDialog
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT

import com.squareup.timessquare.CalendarPickerView
import com.squareup.timessquare.CalendarPickerView.SelectionMode
import com.squareup.timessquare.sample.R

import com.squareup.timessquare.CalendarPickerView.OnDateSelectedListener
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : Activity() {
    private var calendar: CalendarPickerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.sample_calendar_picker)

        setUpCalendar()

        setUpListeners()
    }


    private fun setUpListeners() {
        calendar!!.setOnDateSelectedListener(object : OnDateSelectedListener {
            override fun onDateUnselected(date: Date) {}

            override fun onDateSelected(date: Date) {
                Log.d(TAG, "Selected time in millis: " + calendar!!.selectedDate.time)
                val dateFormated = getDateFromMs(date)
                val toast = "Selected: " + dateFormated
                Toast.makeText(this@MainActivity, toast, LENGTH_SHORT).show()
            }
        })

    }

    private fun getDateFromMs(date: Date): String {
        val msToDate = Date(date.time)
        val df = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        return df.format(msToDate)
    }

    private fun setUpCalendar() {
        val nextYear = Calendar.getInstance()
        nextYear.add(Calendar.YEAR, 1)

        val lastYear = Calendar.getInstance()
        lastYear.add(Calendar.YEAR, -1)

        calendar = findViewById<View>(R.id.calendar_view) as CalendarPickerView
        calendar!!.init(lastYear.time, nextYear.time) //
                .inMode(SelectionMode.SINGLE) //
                .withSelectedDate(Date())
    }


    companion object {
        val TAG = "Grays planner"
    }
}


