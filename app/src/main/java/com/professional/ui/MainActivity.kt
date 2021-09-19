package com.professional.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.professional.R
import com.professional.ui.mainfragment.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.container, MainFragment.newInstance()).commit()
    }
}