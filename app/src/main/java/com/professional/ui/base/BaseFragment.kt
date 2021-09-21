package com.professional.ui.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.professional.models.AppState
import com.professional.presentors.Presenter
import com.professional.views.MainView
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment : Fragment(), MainView, HasAndroidInjector {
    protected val presenter: Presenter<MainView, AppState> by lazy {
        createPresenter()
    }

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>
    abstract override fun renderData(appState: AppState)
    abstract fun createPresenter(): Presenter<MainView, AppState>

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }


    override fun androidInjector(): AndroidInjector<Any> = androidInjector
    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onDestroy() {
        presenter.detachView(this)
        super.onDestroy()
    }
}