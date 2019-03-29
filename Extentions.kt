package com.safetyinoffice.safety.util

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.text.Editable
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlin.reflect.KClass
import android.net.NetworkInfo
import android.net.ConnectivityManager
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.DialogInterface
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import com.google.android.material.textfield.TextInputEditText
import java.util.function.Consumer


fun AppCompatActivity.goToActivity(cls: KClass<*>) {
    startActivity(Intent(this, cls.java))
}

fun Fragment.goToActivity(cls: KClass<*>) {
    startActivity(Intent(context, cls.java))
}

fun AppCompatActivity.makeToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.makeToast(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun Editable?.trimString(): String = this.toString().trim()

fun EditText.getStringText(): String = this.text.toString().trim()

fun AppCompatActivity.isInternetAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).state == NetworkInfo.State.CONNECTED ||
            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).state == NetworkInfo.State.CONNECTED

}

fun Fragment.isInternetAvailable(): Boolean {
    val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).state == NetworkInfo.State.CONNECTED ||
            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).state == NetworkInfo.State.CONNECTED

}

fun AppCompatActivity.internetNotAvailableException() {
    makeToast("internet Not Available Exception")
}

fun Fragment.internetNotAvaliableException() {
    makeToast("internet Not Available Exception")
}

fun Fragment.hideKeyboard() {
    val activity = context as AppCompatActivity
    val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = activity.currentFocus
    if (view == null) {
        view = View(activity)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun AppCompatActivity.hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = currentFocus
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun AppCompatActivity.showOkDialog(title: String, message: String, okClickListener: DialogInterface.OnClickListener?) {
    val aDialog = AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setCancelable(false)

    if (okClickListener == null) {
        aDialog.setPositiveButton("OK") { dialog, _ -> dialog?.dismiss() }
    } else {
        aDialog.setPositiveButton("OK", okClickListener)
    }

    aDialog.show()
}

fun Fragment.showOkDialog(title: String, message: String, okClickListener: DialogInterface.OnClickListener?) {
    val aDialog = AlertDialog.Builder(context!!)
        .setTitle(title)
        .setMessage(message)
        .setCancelable(false)

    if (okClickListener == null) {
        aDialog.setPositiveButton("OK") { dialog, _ -> dialog?.dismiss() }
    } else {
        aDialog.setPositiveButton("OK", okClickListener)
    }

    aDialog.show()

}

fun EditText.makeEmpty() {
    setText("")
}


fun Fragment.getBearerToken() = "Bearer ${CommonUtils(context!!).getStringPref(AppConstants.SESSION_TOKEN)}"