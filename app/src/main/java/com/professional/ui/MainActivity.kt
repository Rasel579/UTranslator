package com.professional.ui

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.professional.R
import com.professional.ui.mainfragment.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            splashScreen.setOnExitAnimationListener {
                val animation = ObjectAnimator
                    .ofFloat(
                        it,
                        "translationX",
                        this.resources.displayMetrics.widthPixels.toFloat()
                    )
                animation.duration = 2000
                animation.start()
            }
        }
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, MainFragment.newInstance())
            .commit()
    }

    override fun onBackPressed() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, MainFragment.newInstance())
            .commit()
    }
}