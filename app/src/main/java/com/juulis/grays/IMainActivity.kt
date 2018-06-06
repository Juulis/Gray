package com.juulis.grays


interface IMainActivity {
    fun setTitle(title: String)
    fun inflateFragment(fragmentTag: String, date: Long = 0, setDateMode: Boolean = false)
    fun getStoredDate():Long
    fun setStoredDate(date: Long)
    fun showSnackBar(msg: String, color: Int)
    fun getStartToggleButtons():List<Boolean>
    fun setStartToggleButtons(i: Int, toggle: Boolean)
}