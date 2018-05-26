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
    private var theDialog: AlertDialog? = null
    private var dialogView: CalendarPickerView? = null

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

    private fun getDateFromMs(date: Date):String{
        val msToDate = Date(date.time)
        val df = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        return df.format(msToDate)
    }

    private fun setUpCalendar(){
        val nextYear = Calendar.getInstance()
        nextYear.add(Calendar.YEAR, 1)

        val lastYear = Calendar.getInstance()
        lastYear.add(Calendar.YEAR, -1)

        calendar = findViewById<View>(R.id.calendar_view) as CalendarPickerView
        calendar!!.init(lastYear.time, nextYear.time) //
                .inMode(SelectionMode.SINGLE) //
                .withSelectedDate(Date())
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        val applyFixes = theDialog != null && theDialog!!.isShowing
        if (applyFixes) {
            Log.d(TAG, "Config change: unfix the dimens so I'll get remeasured!")
            dialogView!!.unfixDialogDimens()
        }
        super.onConfigurationChanged(newConfig)
        if (applyFixes) {
            dialogView!!.post {
                Log.d(TAG, "Config change done: re-fix the dimens!")
                dialogView!!.fixDialogDimens()
            }
        }
    }

    companion object {
        private val TAG = "SampleTimesSquareActivi"
    }
}


//      TODO: put this snippet in a fragment later?
//    private fun showCalendarInDialog(title: String, layoutResId: Int) {
//        dialogView = layoutInflater.inflate(layoutResId, null, false) as CalendarPickerView
//        theDialog = AlertDialog.Builder(this) //
//                .setTitle(title)
//                .setView(dialogView)
//                .setNeutralButton("Dismiss") { dialogInterface, i -> dialogInterface.dismiss() }
//                .create()
//        theDialog!!.setOnShowListener {
//            Log.d(TAG, "onShow: fix the dimens!")
//            dialogView!!.fixDialogDimens()
//        }
//        theDialog!!.show()
//    }