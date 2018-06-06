package com.juulis.grays


import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.fragment_settings.*
import java.util.*

class SettingsFragment : Fragment(), View.OnClickListener {

    private lateinit var iMainActivity: IMainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        iMainActivity = activity as IMainActivity
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        set_startdate_button.setOnClickListener(this)
        week_reverse_button.setOnClickListener(this)
        first_notice_button.setOnClickListener(this)
        second_notice_button.setOnClickListener(this)
        third_notice_button.setOnClickListener(this)
        fourth_notice_button.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.set_startdate_button -> openDatePicker()
            R.id.week_reverse_button -> reverseWeek()
            R.id.first_notice_button -> openTimePicker(v.id)
            R.id.second_notice_button -> openTimePicker(v.id)
            R.id.third_notice_button -> openTimePicker(v.id)
            R.id.fourth_notice_button -> openTimePicker(v.id)
        }
    }

    private fun openTimePicker(id: Int) {
        iMainActivity.inflateFragment(getString(R.string.time_picker_fragment))

    }

    private fun reverseWeek() {
    }

    private fun openDatePicker() {
        iMainActivity.inflateFragment(getString(R.string.calendar_fragment),setDateMode = true)
    }


}
