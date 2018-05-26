package com.juulis.grays

import android.app.AlertDialog
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import com.squareup.timessquare.CalendarPickerView

class ScheduleFragment : Fragment() {

    private var theDialog: AlertDialog? = null
    private var dialogView: CalendarPickerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    //      TODO this is from original sample code
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

    override fun onConfigurationChanged(newConfig: Configuration) {
        val applyFixes = theDialog != null && theDialog!!.isShowing
        if (applyFixes) {
            Log.d(MainActivity.TAG, "Config change: unfix the dimens so I'll get remeasured!")
            dialogView!!.unfixDialogDimens()
        }
        super.onConfigurationChanged(newConfig)
        if (applyFixes) {
            dialogView!!.post {
                Log.d(MainActivity.TAG, "Config change done: re-fix the dimens!")
                dialogView!!.fixDialogDimens()
            }
        }
    }

}