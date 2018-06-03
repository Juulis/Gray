package com.juulis.grays

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.widget.TextView
import java.util.*
import android.view.*


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
        setStyle(DialogFragment.STYLE_NO_TITLE,R.style.Theme_CustomDialog)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_medication, container, false)

        medication = Medication(startDate,selectedDate)

        val day = view.findViewById<TextView>(R.id.medication_day)
        val fase = view.findViewById<TextView>(R.id.medication_fase)
        val bulkAmount = view.findViewById<TextView>(R.id.medication_bulk_amount)
        val pilAmount = view.findViewById<TextView>(R.id.medication_pil_amount)
        val freq = view.findViewById<TextView>(R.id.medication_frequency)

        day.text = CalendarFragment.formatDate(selectedDate)
        fase.text = medication.fase
        bulkAmount.text=medication.bulkAmount.toString()
        pilAmount.text=medication.pilAmount.toString()
        freq.text=medication.bulkFreq.toString()

        val params = dialog.window!!
                .attributes
        params.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.attributes = params

        return view
    }

    override fun onStart() {
        super.onStart()
        dialog.window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
    }

}

