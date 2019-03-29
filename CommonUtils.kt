package com.safetyinoffice.safety.util

import android.app.ProgressDialog
import android.content.Context
import kotlinx.android.synthetic.main.layout_toolbar.*
import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.edit
import com.safetyinoffice.safety.R


class CommonUtils(val context: Context) {
    private var progressDialog: ProgressDialog? = null
    private val PREFRANCES_KEY: String = "com.safetyinoffice.safety"


    fun showLoader(title: String = "Loading", message: String = "Please Wait...") {

        hideLoader()

        progressDialog = ProgressDialog(context).apply {
            setTitle(title)
            setMessage(message)
            setProgressStyle(ProgressDialog.STYLE_SPINNER)
            setCancelable(false)
        }

        progressDialog?.show()


    }

    fun hideLoader() {
        progressDialog?.takeIf { (progressDialog as ProgressDialog).isShowing }?.apply { dismiss() }
    }

    private fun getSharefPrefs() = context.getSharedPreferences(PREFRANCES_KEY, Context.MODE_PRIVATE);

    fun setStringPref(key: String, value: String?) = getSharefPrefs().edit { putString(key, value) }

    fun getStringPref(key: String) = getSharefPrefs().getString(key, "")

}