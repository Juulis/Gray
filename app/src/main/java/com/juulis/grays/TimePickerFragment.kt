package com.juulis.grays


import android.app.Activity
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class TimePickerFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_time_picker, container, false)


        val params = dialog.window!!.attributes
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_CustomDialog)
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
