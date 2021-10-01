package com.professional.ui.base

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.Fragment
import com.professional.R
import com.test_app.model.AppState
import com.professional.viewmodels.base.BaseViewModel
import com.test_app.utils.ImageLoader

abstract class BaseFragment : Fragment() {
    abstract fun renderData(appState: AppState)
    abstract val viewModel: BaseViewModel
    protected val imageLoader = ImageLoader
    protected fun showFragment(fragment: Fragment) {
        parentFragmentManager
            .beginTransaction()
            .replace(
                R.id.container,
                fragment
            ).addToBackStack("main")
            .commit()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
    }

    companion object {
        const val BASE_FRAGMENT_TAG = "base fragment tag"
    }
}