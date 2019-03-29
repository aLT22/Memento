package com.bytebuilding.memento.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.bytebuilding.memento.R

/**
 * Launch another activity
 * */
@SuppressLint("ObsoleteSdkInt")
inline fun <reified T : Any> AppCompatActivity.launchActivity(
        requestCode: Int = -1,
        options: Bundle? = null,
        noinline init: Intent.() -> Unit = {}
) {

    val intent = newIntent<T>()
    intent.init()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        startActivityForResult(intent, requestCode, options)
    } else {
        startActivityForResult(intent, requestCode)
    }
}

@SuppressLint("ObsoleteSdkInt")
inline fun <reified T : Any> AppCompatActivity.launchActivityAndFinishCurrent(
        requestCode: Int = -1,
        options: Bundle? = null,
        noinline init: Intent.() -> Unit = {}
) {

    val intent = newIntent<T>()
    intent.init()
    this.finish()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        startActivityForResult(intent, requestCode, options)
    } else {
        startActivityForResult(intent, requestCode)
    }
}
/**
 * Launch another activity
 * */

/**
 * Keyboard options
 * */
fun AppCompatActivity.hideKeyboard() {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = this.currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}
/**
 * Keyboard options
 * */

/**
 * Toolbar/ActionBar
 * */
fun AppCompatActivity.setUpToolbar(
        toolbar: Toolbar,
        isHomeAsUpEnabled: Boolean = false,
        @DrawableRes homeAsUpIndicatorResId: Int = 0,
        @ColorRes toolbarColor: Int = R.color.colorPrimary,
        title: CharSequence,
        @ColorRes titleColor: Int = R.color.colorWhite,
        subtitle: CharSequence? = null,
        @ColorRes subtitleColor: Int = R.color.colorWhite
): Toolbar {
    val spannableTitle = setUpSpannableTitle(title.toString())
    val spannableSubtitle = setUpSpannableTitle(subtitle.toString())

    toolbar.apply {
        setBackgroundColor(ContextCompat.getColor(this@setUpToolbar, toolbarColor))

        setTitle(spannableTitle)
        setTitleTextColor(ContextCompat.getColor(this@setUpToolbar, titleColor))

        if (!spannableSubtitle.isBlank()) {
            setSubtitle(spannableSubtitle)
            setSubtitleTextColor(ContextCompat.getColor(this@setUpToolbar, subtitleColor))
        }
    }

    setSupportActionBar(toolbar)

    supportActionBar?.setDisplayHomeAsUpEnabled(isHomeAsUpEnabled)
    if (homeAsUpIndicatorResId != 0) {
        supportActionBar?.setHomeAsUpIndicator(homeAsUpIndicatorResId)
    }

    return toolbar
}

fun AppCompatActivity.setUpToolbar(
        toolbar: Toolbar,
        isHomeAsUpEnabled: Boolean = false,
        @DrawableRes homeAsUpIndicatorResId: Int = 0,
        @ColorRes toolbarColor: Int = R.color.colorPrimary,
        @StringRes title: Int,
        @ColorRes titleColor: Int = R.color.colorWhite,
        @StringRes subtitle: Int? = null,
        @ColorRes subtitleColor: Int = R.color.colorWhite
): Toolbar {
    val spannableTitle = setUpSpannableTitle(title)
    val spannableSubtitle =
            if (subtitle != null) setUpSpannableTitle(subtitle)
            else null

    toolbar.apply {
        setBackgroundColor(ContextCompat.getColor(this@setUpToolbar, toolbarColor))

        setTitle(spannableTitle)
        setTitleTextColor(ContextCompat.getColor(this@setUpToolbar, titleColor))

        spannableSubtitle?.let { subtitle ->
            setSubtitle(subtitle)
            setSubtitleTextColor(ContextCompat.getColor(this@setUpToolbar, subtitleColor))
        }
    }

    setSupportActionBar(toolbar)

    supportActionBar?.setDisplayHomeAsUpEnabled(isHomeAsUpEnabled)
    if (homeAsUpIndicatorResId != 0) {
        supportActionBar?.setHomeAsUpIndicator(homeAsUpIndicatorResId)
    }

    return toolbar
}

@SuppressLint("ResourceType")
private fun AppCompatActivity.setUpSpannableTitle(title: Int): SpannableString {
    val spannableTitle = SpannableString(getString(title))
    spannableTitle.setSpan(TypefaceSpan(this, R.font.crimson_text_bold),
            0,
            spannableTitle.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return spannableTitle
}

@SuppressLint("ResourceType")
private fun AppCompatActivity.setUpSpannableTitle(title: String): SpannableString {
    val spannableTitle = SpannableString(title)
    spannableTitle.setSpan(TypefaceSpan(this, R.font.crimson_text_bold),
            0,
            spannableTitle.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return spannableTitle
}
/**
 * Toolbar/ActionBar
 * */