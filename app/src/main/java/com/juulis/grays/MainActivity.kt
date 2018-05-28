package com.juulis.grays

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.widget.TextView

interface IMainActivity {
    fun setTitle(title: String)
    fun inflateFragment(fragmentTag: String, date: Long = 0)
}

class MainActivity : FragmentActivity(), IMainActivity {
    private val TAG = "MainActivity"
    private var selectedDate: Long? = null

    private lateinit var toolbar: TextView

    override fun inflateFragment(fragmentTag: String, date: Long) {
        Log.d(TAG,"received tag: "+fragmentTag)
        when (fragmentTag) {
            getString(R.string.calendar_fragment) -> doTransaction(CalendarFragment(), fragmentTag,true)
            getString(R.string.medication_fragment) -> doTransaction(MedicationFragment(),fragmentTag,true,date)
            //getString(R.string.settings_fragment) -> doTransaction(SettingsFragment(),fragmentTag,true,date)
        }
    }

    override fun setTitle(title: String) {
        toolbar.text = title
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        toolbar = findViewById(R.id.toolbar_title)

        init()
    }

    private fun init() {
        val fragment = StartFragment()
        doTransaction(fragment, getString(R.string.fragment_start), false, 0)
    }

    private fun doTransaction(fragment: Fragment, tag: String, addToBackStack: Boolean, selectedDate: Long = 0) {
        Log.d(TAG,"inflating... : "+tag)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment, tag)
        if (addToBackStack) {
            transaction.addToBackStack(tag)
        }
        transaction.commit()
        if(!selectedDate.equals(0))
        this.selectedDate = selectedDate
    }
}


