package com.juulis.grays

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        val startDate = iMainActivity.getStoredDate()
        val medication = Medication(Date(startDate), Date())

        start_bulk_amount.text = getString(R.string.start_bulk_amount, medication.bulkAmount)
        start_pil_amount.text = getString(R.string.start_pill_amount, (medication.pilAmount))

        if(startDate!=null)
            start_day_of_progress.text = getString(R.string.start_day_of_progress, medication.daysProgress)
        else start_day_of_progress.text = getString(R.string.start_day_of_progress, 0)

        start_frequency.text = getString(R.string.start_frequency,medication.bulkFreq)

        start_calendar_button.setOnClickListener(this)
        start_settings_button.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.start_calendar_button -> iMainActivity.inflateFragment(getString(R.string.calendar_fragment))
            R.id.start_settings_button -> iMainActivity.inflateFragment(getString(R.string.settings_fragment))
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
