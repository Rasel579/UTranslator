package com.professional.ui.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.professional.models.AppState
import com.professional.viewmodels.base.BaseViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment : Fragment(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>
    abstract fun renderData(appState: AppState)
    abstract val viewModel: BaseViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }


    override fun androidInjector(): AndroidInjector<Any> = androidInjector

}