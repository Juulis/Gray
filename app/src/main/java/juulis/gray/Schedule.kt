package juulis.gray

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.squareup.timessquare.CalendarPickerView
import java.util.*
import com.squareup.timessquare.CalendarPickerView.SelectionMode
import com.squareup.timessquare.CalendarCellDecorator
import com.squareup.timessquare.DefaultDayViewAdapter


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Schedule.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Schedule.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class Schedule : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        val nextYear = Calendar.getInstance()
        nextYear.add(Calendar.YEAR, 1)

        val lastYear = Calendar.getInstance()
        lastYear.add(Calendar.YEAR, -1)

        val calendar = activity!!.findViewById<CalendarPickerView>(R.id.calendar_view)
        calendar.init(lastYear.time, nextYear.time)
                .inMode(SelectionMode.SINGLE)
                .withSelectedDate(Date())


        val single = activity!!.findViewById<Button>(R.id.button_single)
        single!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                single.isEnabled

                calendar.setCustomDayView(DefaultDayViewAdapter())
                calendar.decorators = Collections.emptyList<CalendarCellDecorator>()
                calendar.init(lastYear.time, nextYear.time) //
                        .inMode(SelectionMode.SINGLE) //
                        .withSelectedDate(Date())
            }
        })

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_schedule, container, false)

        //setup calendar
        val nextYear = Calendar.getInstance()
        nextYear.add(Calendar.YEAR, 1)
        val calendar: CalendarPickerView = view.findViewById(R.id.calendar_view)
        val today = Date()
        calendar.init(today, nextYear.time)?.withSelectedDate(today)

        return view
    }

    /*// TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }*/

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Schedule.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                Schedule().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
