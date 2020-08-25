package com.startted.global.base

import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.startted.global.R
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity : AppCompatActivity() {

    fun showError(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
            .setBackgroundTint(ContextCompat.getColor(applicationContext, R.color.coralRed))
            .setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
            .apply {
                view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)?.let {
                    it.maxLines = 5
                }
            }
            .show()
    }

}