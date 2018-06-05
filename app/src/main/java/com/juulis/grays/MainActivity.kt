package com.juulis.grays

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import java.time.Instant
import java.time.LocalDateTime.now
import java.time.ZoneId
import java.util.*


class MainActivity : AppCompatActivity(), IMainActivity {
    private val TAG = "Testing in MainActivity "

    override fun inflateFragment(fragmentTag: String, date: Long, setDateMode: Boolean) {
        Log.d(TAG, "received tag: " + fragmentTag)
        when (fragmentTag) {
            getString(R.string.calendar_fragment) -> {
                if (setDateMode) {
                    doTransaction(CalendarFragment(), fragmentTag, true, setDateMode = true)
                } else doTransaction(CalendarFragment(), fragmentTag, true)
            }
            getString(R.string.medication_fragment) -> doTransaction(MedicationFragment(), fragmentTag, false, date)
            getString(R.string.no_data_on_date_dialog) -> doTransaction(NoDataOnDateDialog(), fragmentTag, false)
            getString(R.string.settings_fragment) -> doTransaction(SettingsFragment(), fragmentTag, true, date)
            getString(R.string.set_start_date_dialog) -> doTransaction(SetDateDialog(), fragmentTag, false, date)
        }
    }

    override fun setStartToggleButtons(i: Int, toggle: Boolean) {
        val prefs = this.getSharedPreferences("com.juulis.grays", Context.MODE_PRIVATE)
        when (i) {
            0 -> prefs.edit().putBoolean(getString(R.string.took_first_medication), toggle).apply()
            1 -> prefs.edit().putBoolean(getString(R.string.took_second_medication), toggle).apply()
            2 -> prefs.edit().putBoolean(getString(R.string.took_third_medication), toggle).apply()
            3 -> prefs.edit().putBoolean(getString(R.string.took_fourth_medication), toggle).apply()
        }
        prefs.edit().putLong(getString(R.string.date_of_toggle), Date().time).apply()
    }

    override fun getStartToggleButtons(): List<Boolean> {
        val toggleButtons = mutableListOf<Boolean>()
        val prefs = this.getSharedPreferences("com.juulis.grays", Context.MODE_PRIVATE)
        val dateOfSaveMs = prefs.getLong(getString(R.string.date_of_toggle), Date().time)
        val dateOfSave = Instant.ofEpochMilli(dateOfSaveMs).atZone(ZoneId.systemDefault()).toLocalDateTime()
        val today = now()
        if (today.dayOfYear == dateOfSave.dayOfYear && dateOfSave.hour < 4) {
            toggleButtons.add(prefs.getBoolean(getString(R.string.took_first_medication), false))
            toggleButtons.add(prefs.getBoolean(getString(R.string.took_second_medication), false))
            toggleButtons.add(prefs.getBoolean(getString(R.string.took_third_medication), false))
            toggleButtons.add(prefs.getBoolean(getString(R.string.took_fourth_medication), false))
        } else {
            for (i in 0..3) {
                toggleButtons.add(i, false)
            }
        }
        return toggleButtons
    }

    override fun setStoredDate(date: Long) {
        val prefs = this.getSharedPreferences("com.juulis.grays", Context.MODE_PRIVATE)
        prefs.edit().putLong(getString(R.string.saved_start_date), date).apply()
    }

    override fun getStoredDate(): Long {
        val prefs = this.getSharedPreferences("com.juulis.grays", Context.MODE_PRIVATE)
        return prefs.getLong(getString(R.string.saved_start_date), Date().time)
    }

    override fun setTitle(title: String) {
    }

    override fun showSnackBar(msg: String, color: Int) {
        Snackbar.make(findViewById(R.id.main_container), msg, Snackbar.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
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

