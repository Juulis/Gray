package com.juulis.grays

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.widget.TextView
import java.util.*


class MainActivity : FragmentActivity(), IMainActivity {
    private val TAG = "Testing in MainActivity "
    private var selectedDate: Long? = null
    private lateinit var toolbar: TextView

    override fun inflateFragment(fragmentTag: String, date: Long, setDateMode: Boolean) {
        Log.d(TAG, "received tag: " + fragmentTag)
        when (fragmentTag) {
            getString(R.string.calendar_fragment) -> {
                if (setDateMode){
                    doTransaction(CalendarFragment(), fragmentTag, true, setDateMode = true)
                }else doTransaction(CalendarFragment(), fragmentTag, true)
            }
            getString(R.string.medication_fragment) -> doTransaction(MedicationFragment(), fragmentTag, false, date)
            getString(R.string.no_data_on_date_dialog) -> doTransaction(NoDataOnDateDialog(), fragmentTag, false)
            getString(R.string.settings_fragment) -> doTransaction(SettingsFragment(), fragmentTag, true, date)
            getString(R.string.set_start_date_dialog) -> doTransaction(SetDateDialog(), fragmentTag, true, date)
        }
    }

    override fun getStoredDate(): Long {
        val prefs = this.getSharedPreferences(
                "com.juulis.grays", Context.MODE_PRIVATE)
        return prefs.getLong("start_date", -1L)
    }

    override fun setTitle(title: String) {
        //toolbar.text = title
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        //toolbar = findViewById(R.id.toolbar_title)

        init()
    }

    private fun init() {
        val fragment = StartFragment()
        doTransaction(fragment, getString(R.string.fragment_start), false, 0)
    }

    private fun doTransaction(fragment: Fragment, tag: String, addToBackStack: Boolean, selectedDate: Long? = null, setDateMode: Boolean = false) {
        val transaction = supportFragmentManager.beginTransaction()
        val bundle = Bundle()

        if (setDateMode)
            bundle.putBoolean(getString(R.string.set_startdate_boolean), setDateMode)
        if (selectedDate != null) {
            bundle.putLong(getString(R.string.date_intent), selectedDate)
        }

        fragment.arguments = bundle

        if (fragment is DialogFragment) {
            transaction.add(fragment, tag)
        } else {
            transaction.replace(R.id.main_container, fragment, tag)
        }
        if (addToBackStack) {
            transaction.addToBackStack(tag)
        }
        transaction.commit()
    }
}

