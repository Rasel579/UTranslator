package com.professional.ui.base

import androidx.fragment.app.Fragment
import com.professional.models.AppState
import com.professional.viewmodels.base.BaseViewModel

abstract class BaseFragment : Fragment() {
    abstract fun renderData(appState: AppState)
    abstract val viewModel: BaseViewModel
}