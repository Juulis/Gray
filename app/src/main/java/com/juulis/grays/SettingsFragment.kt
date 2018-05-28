package com.juulis.grays


import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import java.util.*

class SettingsFragment : Fragment(), View.OnClickListener {

    private lateinit var iMainActivity: IMainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        iMainActivity = activity as IMainActivity
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        val setStartDate = view.findViewById<Button>(R.id.set_startdate_button)
        val reverseWeek = view.findViewById<Button>(R.id.week_reverse_button)
        val addNoticeOne = view.findViewById<Button>(R.id.first_notice_button)
        val addNoticeTwo = view.findViewById<Button>(R.id.second_notice_button)
        val addNoticeThree = view.findViewById<Button>(R.id.third_notice_button)
        val addNoticeFour = view.findViewById<Button>(R.id.fourth_notice_button)

        setStartDate.setOnClickListener(this)
        reverseWeek.setOnClickListener(this)
        addNoticeOne.setOnClickListener(this)
        addNoticeTwo.setOnClickListener(this)
        addNoticeThree.setOnClickListener(this)
        addNoticeFour.setOnClickListener(this)

        return view
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

    }

    private fun reverseWeek() {
    }

    private fun openDatePicker() {
        iMainActivity.inflateFragment(getString(R.string.calendar_fragment),setDateMode = true)
    }


}
