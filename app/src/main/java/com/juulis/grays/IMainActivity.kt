package com.juulis.grays

interface IMainActivity {
    fun setTitle(title: String)
    fun inflateFragment(fragmentTag: String, date: Long = 0, setDateMode: Boolean = false)
    fun getStoredDate():Long
}