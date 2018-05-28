package com.juulis.grays

import android.app.AlertDialog
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.timessquare.CalendarPickerView
import java.text.SimpleDateFormat
import java.util.*

class CalendarFragment : Fragment() {


    private var theDialog: AlertDialog? = null
    private lateinit var calendar: CalendarPickerView
    private lateinit var iMainActivity: IMainActivity
    private val TAG = "Testing in CalendarFragment "
    private var isSettingDate: Boolean? = false

    companion object {
        fun formatDate(date: Date): String {
            val df = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            return df.format(date)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iMainActivity.setTitle(tag!!)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        iMainActivity = activity as IMainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        isSettingDate = arguments?.getBoolean(getString(R.string.set_startdate_boolean), false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpCalendar(view)
        setUpListeners()
    }


    private fun setUpListeners() {
        calendar.setOnDateSelectedListener(object : CalendarPickerView.OnDateSelectedListener {
            override fun onDateUnselected(date: Date) {}

            override fun onDateSelected(date: Date) {
                Log.d(TAG, "date selected: " + formatDate(date))
                if (!isSettingDate!!) {
                    showMedicationDialog(date)
                }else showSetDateDialog(date)
            }
        })
    }

    private fun showSetDateDialog(date: Date) {
        iMainActivity.inflateFragment(getString(R.string.set_start_date_dialog),date = date.time)
    }

    private fun showMedicationDialog(date: Date) {
        Log.d(TAG, "showing medicationDialog")
        //if(dateOk(date))
        if (dateOk(date)) {
            Log.d(TAG, "date=date")
            iMainActivity.inflateFragment(getString(R.string.medication_fragment), date.time)
        } else
            iMainActivity.inflateFragment((getString(R.string.no_data_on_date_dialog)))
    }

    private fun dateOk(date: Date): Boolean {
        val medication = Medication(date, Date(iMainActivity.getStoredDate()))
        if (medication.fase == null)
            return false

        return true
    }


    private fun setUpCalendar(view: View) {
        val nextYear = Calendar.getInstance()
        nextYear.add(Calendar.YEAR, 1)

        val lastYear = Calendar.getInstance()
        lastYear.add(Calendar.YEAR, -1)

        calendar = view.findViewById(R.id.calendar_view)
        calendar.init(lastYear.time, nextYear.time) //
                .inMode(CalendarPickerView.SelectionMode.SINGLE) //
                .withSelectedDate(Date())
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        val applyFixes = theDialog != null && theDialog!!.isShowing
        if (applyFixes) {
            Log.d(TAG, "Config change: unfix the dimens so I'll get remeasured!")
            calendar.unfixDialogDimens()
        }
        super.onConfigurationChanged(newConfig)
        if (applyFixes) {
            calendar.post {
                Log.d(TAG, "Config change done: re-fix the dimens!")
                calendar.fixDialogDimens()
            }
        }
    }

    interface CalendarFragmentOnDaySelect {
        fun onDayClicked(dateInMs: Long)

    }

}
