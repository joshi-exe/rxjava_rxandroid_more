package com.yash.rxandmore.utils

import android.content.Context
import android.widget.Toast
import java.util.*

/**
 * Created by Joshi on 07-04-2020.
 */
object AppUtils {

    fun shortToast(context: Context, message: String) =
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    fun longToast(context: Context, message: String) =
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()


    fun isValidPinCode(pinCode: String): Boolean {
        val ePattern = "^[1-9][0-9]{5}$"
        val p = java.util.regex.Pattern.compile(ePattern)
        val m = p.matcher(pinCode.toUpperCase(Locale.ENGLISH))
        return m.matches()
    }

    fun isValidMobileNo(mobileNumber: String): Boolean {
        val ePattern = "^[6-9][0-9]{9}$"
        val p = java.util.regex.Pattern.compile(ePattern)
        val m = p.matcher(mobileNumber.toUpperCase(Locale.ENGLISH))
        return m.matches()
    }

    fun isValidEmailId(emailId: String): Boolean {
        val ePattern = "^[\\\\w!#\$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#\$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}\$"
        val p = java.util.regex.Pattern.compile(ePattern)
        val m = p.matcher(emailId.toUpperCase(Locale.ENGLISH))
        return m.matches()
    }
}