package com.professional.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.professional.R
import com.test_app.core.baseui.BaseFragment
import com.professional.ui.mainfragment.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, MainFragment.newInstance())
            .addToBackStack(BaseFragment.BASE_FRAGMENT_TAG)
            .commit()
    }

    override fun onBackPressed() {
        supportFragmentManager.popBackStackImmediate(BaseFragment.BASE_FRAGMENT_TAG, 0)
    }
}