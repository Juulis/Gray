package com.juulis.grays

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.*

class NoDataOnDateDialog: DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dialog_no_values,container,false)
        dialog.setCanceledOnTouchOutside(true)
        setStyle(DialogFragment.STYLE_NO_TITLE,R.style.Theme_CustomDialog)

        val params = dialog.window!!.attributes
        params.gravity = Gravity.CENTER
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.attributes = params

        return view
    }
}