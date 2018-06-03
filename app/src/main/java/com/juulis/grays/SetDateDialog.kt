package com.juulis.grays


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import java.util.*

class SetDateDialog : DialogFragment(), View.OnClickListener {
    private lateinit var iMainActivity: IMainActivity
    private lateinit var selectedDate: Date
    private val TAG = "Testing in setDateDialog "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        val dateInMs = bundle?.getLong(getString(R.string.date_intent))
        if (dateInMs != null)
            selectedDate = Date(dateInMs)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_set_start_date_dialog, container, false)

        iMainActivity = activity as IMainActivity

        val okButton = view.findViewById<Button>(R.id.set_start_date_ok_button)
        val cancelButton = view.findViewById<Button>(R.id.set_start_date_cancel_button)
        val dateLabel = view.findViewById<TextView>(R.id.set_start_date_date_label)

        dateLabel.text = CalendarFragment.formatDate(selectedDate)
        Log.d(TAG, "setting selectedDate: "+dateLabel.text)

        okButton.setOnClickListener(this)
        cancelButton.setOnClickListener(this)


        return view
    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.set_start_date_ok_button -> setStartDate(selectedDate)
            R.id.set_start_date_cancel_button -> cancelDialog()
        }
    }

    private fun setStartDate(date: Date) {
        iMainActivity.setStoredDate(date.time)
        Log.d(TAG,"saved date: "+CalendarFragment.formatDate(date))
        Snackbar.make(activity!!.findViewById<CoordinatorLayout>(R.id.main_container),getString(R.string.saved_start_date_msg,CalendarFragment.formatDate(date)), Snackbar.LENGTH_LONG).show()
        dialog.dismiss()
        activity!!.onBackPressed()
    }

    private fun cancelDialog() {
        dialog.dismiss()
    }
}
