package com.juulis.grays

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MedicationFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MedicationFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class MedicationFragment : Fragment() {
    private lateinit var selectedDate: Date

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        val dateInMs = bundle?.getLong("dateInMs")
        if (dateInMs != null)
            selectedDate = Date(dateInMs)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_medication, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }


}

