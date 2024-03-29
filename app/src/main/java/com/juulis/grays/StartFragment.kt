package com.juulis.grays

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ToggleButton
import kotlinx.android.synthetic.main.fragment_start.*
import java.util.*
import android.support.design.widget.Snackbar


class StartFragment : Fragment(), View.OnClickListener {

    private lateinit var iMainActivity: IMainActivity
    private val TAG = "Testing in StartFragment "
    private var doseButtons: MutableList<ToggleButton> = mutableListOf()

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
        val doseButtonContainers: MutableList<LinearLayout> = mutableListOf(toggle_button_1, toggle_button_2, toggle_button_3, toggle_button_4)
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
        doseButtons = mutableListOf(start_first_dose_button, start_second_dose_button, start_third_dose_button, start_fourth_dose_button)

        doseButtons.forEach { btn ->
            if (checkBoxList[doseButtons.indexOf(btn)]) {
                btn.isChecked = true
            }
            btn.setOnClickListener(this)
        }

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            start_calendar_button.id -> iMainActivity.inflateFragment(getString(R.string.calendar_fragment))
            start_settings_button.id -> iMainActivity.inflateFragment(getString(R.string.settings_fragment))
            start_first_dose_button.id -> toggleButton(0)
            start_second_dose_button.id -> toggleButton(1)
            start_third_dose_button.id -> toggleButton(2)
            start_fourth_dose_button.id -> toggleButton(3)
        }
    }

    private fun toggleButton(i: Int) {
        if (!doseButtons[i].isChecked) {
            val mySnackbar = Snackbar.make((start_fragment_layout), getString(R.string.toggle_state_untoggled), Snackbar.LENGTH_LONG)
            mySnackbar.setAction(R.string.undo_string, MyUndoListener(i))
            mySnackbar.show()
            iMainActivity.setStartToggleButtons(i,false)
        }else iMainActivity.setStartToggleButtons(i, true)
    }

    internal inner class MyUndoListener(val i: Int) : View.OnClickListener {
        override fun onClick(v: View) {
            doseButtons[i].toggle()
            iMainActivity.setStartToggleButtons(i,true)
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
