package com.juulis.grays

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import java.util.*
import android.view.*
import kotlinx.android.synthetic.main.fragment_medication.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MedicationFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MedicationFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class MedicationFragment : DialogFragment() {
    private lateinit var medication: Medication
    private lateinit var selectedDate: Date
    private lateinit var iMainActivity: IMainActivity
    private lateinit var startDate: Date

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        val dateInMs = bundle?.getLong(getString(R.string.date_intent))
        if (dateInMs != null)
            selectedDate = Date(dateInMs)

        iMainActivity = activity as MainActivity
        startDate = Date(iMainActivity.getStoredDate())

        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_CustomDialog)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_medication, container, false)

        medication = Medication(startDate, selectedDate)

        val params = dialog.window!!
                .attributes
        params.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.attributes = params

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        medication_date.text = CalendarFragment.formatDate(selectedDate)
        medication_day.text = getString(R.string.start_day_of_progress,medication.daysProgress)
        medication_fase.text = getString(R.string.start_fase,medication.fase)
        medication_bulk.text = getString(R.string.start_bulk_amount,medication.bulkAmount)
        medication_pil.text = getString(R.string.start_pill_amount,medication.pilAmount)
        medication_frequency.text = getString(R.string.start_frequency,medication.frequency)

    }

    override fun onStart() {
        super.onStart()
        dialog.window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
    }

}

