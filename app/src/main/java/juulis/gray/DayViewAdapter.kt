package juulis.gray


import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.squareup.timessquare.CalendarCellView
import com.squareup.timessquare.DayViewAdapter

class SampleDayViewAdapter : DayViewAdapter {
    override fun makeCellView(parent: CalendarCellView) {
        val inflater = LayoutInflater.from(parent.context)
        val layout = inflater.inflate(R.layout.day_view_custom, parent, false)
        parent.addView(layout)
        parent.dayOfMonthTextView = layout.findViewById(R.id.day_view)
    }
}