package com.juulis.grays

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class StartFragment : Fragment(), View.OnClickListener {

    private lateinit var calButton: Button
    private lateinit var settingsButton: Button
    private lateinit var iMainActivity: IMainActivity
    private val TAG = "StartFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_start, container, false)
        calButton = view.findViewById(R.id.start_calendar_button)
        settingsButton = view.findViewById(R.id.start_settings_button)

        calButton.setOnClickListener(this)
        settingsButton.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.start_calendar_button -> iMainActivity.inflateFragment(getString(R.string.calendar_fragment))
            R.id.start_settings_button -> iMainActivity.inflateFragment(getString(R.string.settings_fragment))
        }
    }

    private fun openSettings() {
        Log.d(TAG,"opening Settings")
    }

    private fun openCalendar() {
        Log.d(TAG,"opening Calendar")
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        iMainActivity = activity as IMainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iMainActivity.setTitle(tag!!)
    }
}
