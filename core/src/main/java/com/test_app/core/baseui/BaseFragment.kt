package com.test_app.core.baseui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.Fragment
import com.test_app.core.R
import com.test_app.model.AppState
import com.test_app.core.baseviewmodel.BaseViewModel
import com.test_app.utils.ImageLoader

abstract class BaseFragment : Fragment() {
    abstract fun renderData(appState: AppState)
    abstract val viewModel: BaseViewModel
    protected val imageLoader = ImageLoader
    protected fun showFragment(fragment: Fragment, layoutId: Int) {
        parentFragmentManager
            .beginTransaction()
            .replace(
                layoutId,
                fragment
            ).addToBackStack(null)
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