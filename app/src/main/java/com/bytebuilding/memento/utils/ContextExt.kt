package com.bytebuilding.memento.utils

import android.app.Activity
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar


/**
 * Shared Preferences
 * */
const val PIMSLEUR_PREFERENCES = "PIMSLEUR_PREFERENCES"

const val ONBOARDING = "SP_ONBOARDING"

fun Context.onboardingWasShown() {
    val editor = getSharedPreferences(PIMSLEUR_PREFERENCES, MODE_PRIVATE).edit()

    editor.putBoolean(ONBOARDING, false)

    editor.apply()
}

fun Context.isOnboardingShow(): Boolean {
    val preferences = getSharedPreferences(PIMSLEUR_PREFERENCES, MODE_PRIVATE)

    return preferences.getBoolean(ONBOARDING, true)
}
/**
 * Shared Preferences
 * */

/**
 * Display metrics
 * */
enum class DisplayDensities(val density: Float,
                            val dpi: String) {
    L(density = 0.75f, dpi = "ldpi"),
    M(density = 1.0f, dpi = "mdpi"),
    TV(density = 1.33f, dpi = "tv"),
    H(density = 1.5f, dpi = "hdpi"),
    XH(density = 2.0f, dpi = "xhdpi"),
    XXH(density = 3.0f, dpi = "xxhdpi"),
    XXXH(density = 4.0f, dpi = "xxxhdpi"),
    ERROR(density = -1f, dpi = "error")
}

fun Context.screenDensity(): String {
    val density = resources.displayMetrics.density
    return when (density) {
        in 0f..DisplayDensities.L.density -> "$density ${DisplayDensities.L.dpi}"
        in DisplayDensities.L.density..DisplayDensities.M.density -> "$density ${DisplayDensities.M.dpi}"
        in DisplayDensities.M.density..DisplayDensities.TV.density -> "$density ${DisplayDensities.TV.dpi}"
        in DisplayDensities.TV.density..DisplayDensities.H.density -> "$density ${DisplayDensities.H.dpi}"
        in DisplayDensities.H.density..DisplayDensities.XH.density -> "$density ${DisplayDensities.XH.dpi}"
        in DisplayDensities.XH.density..DisplayDensities.XXH.density -> "$density ${DisplayDensities.XXH.dpi}"
        in DisplayDensities.XXH.density..DisplayDensities.XXXH.density -> "$density ${DisplayDensities.XXXH.dpi}"
        else -> DisplayDensities.ERROR.dpi
    }
}
/**
 * Display metrics
 * */

/**
 * Intent
 * */
inline fun <reified T : Any> Context.newIntent() = Intent(this, T::class.java)
/**
 * Intent
 * */

/**
 * Keyboard
 * */
fun Context.hideKeyboardFrom(view: View) {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}
/**
 * Keyboard
 * */

/**
 * Toast
 * */
fun Context.longToast(msg: CharSequence) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun Context.shortToast(msg: CharSequence) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
/**
 * Toast
 * */

/**
 * Snackbar
 * */
/**
 * Snackbar
 * */