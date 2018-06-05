package com.juulis.grays

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ToggleButton
import kotlinx.android.synthetic.main.fragment_start.*
import java.util.*

class StartFragment : Fragment(), View.OnClickListener {

    private lateinit var iMainActivity: IMainActivity
    private val TAG = "Testing in StartFragment "

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_start, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        start_calendar_button.setOnClickListener(this)
        start_settings_button.setOnClickListener(this)

    }

    override fun onStart() {
        super.onStart()
        val startDate = iMainActivity.getStoredDate()
        val medication = Medication(Date(startDate), Date())
        val doseButtonContainers: MutableList<LinearLayout> = mutableListOf(toggle_button_1,toggle_button_2,toggle_button_3,toggle_button_4)
        start_title.text = getString(R.string.start_title)
        if (medication.fase != null) {
            start_bulk_amount.text = getString(R.string.start_bulk_amount, medication.bulkAmount)
            start_pil_amount.text = getString(R.string.start_pill_amount, medication.pilAmount)
            start_day_of_progress.text = getString(R.string.start_day_of_progress, medication.daysProgress)
            start_frequency.text = getString(R.string.start_frequency, medication.frequency)
            for (i in 0 until medication.frequency!!) {
                doseButtonContainers[i].visibility = View.VISIBLE
            }
        } else start_medication_title.text = getString(R.string.start_no_program_today)
        setupToggleButtons()



    }

    private fun setupToggleButtons() {
        val checkBoxList = iMainActivity.getStartToggleButtons()
        val doseButtons: MutableList<ToggleButton> = mutableListOf(start_first_dose_button, start_second_dose_button, start_third_dose_button, start_fourth_dose_button)

        doseButtons.forEach { btn ->
            if (checkBoxList[doseButtons.indexOf(btn)]) {
                btn.toggle()
            }
            btn.setOnClickListener(this)
        }

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            start_calendar_button.id -> iMainActivity.inflateFragment(getString(R.string.calendar_fragment))
            start_settings_button.id -> iMainActivity.inflateFragment(getString(R.string.settings_fragment))
            start_first_dose_button.id -> iMainActivity.setStartToggleButtons(0, start_first_dose_button.isChecked)
            start_second_dose_button.id -> iMainActivity.setStartToggleButtons(1, start_second_dose_button.isChecked)
            start_third_dose_button.id -> iMainActivity.setStartToggleButtons(2, start_third_dose_button.isChecked)
            start_fourth_dose_button.id -> iMainActivity.setStartToggleButtons(3, start_fourth_dose_button.isChecked)

        }
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
