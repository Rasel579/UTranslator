package com.test_app.historyfeature.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.test_app.historyfeature.databinding.HistoryFragmentBinding
import com.test_app.historyfeature.viewmodel.HistoryViewModel
import com.test_app.model.AppState
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

class HistoryFragment : BottomSheetDialogFragment(), AndroidScopeComponent {
    override val scope: Scope by fragmentScope()
    private val viewBinding: HistoryFragmentBinding by viewBinding(CreateMethod.INFLATE)
    private val viewModel: HistoryViewModel by viewModel(named<HistoryViewModel>())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getData()
    }


    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                viewBinding.historyRecycleView.adapter = HistoryAdapter(appState.data)
            }
            is AppState.Error -> appState.error.message?.let {
                Snackbar
                    .make(viewBinding.root, it, Snackbar.LENGTH_LONG)
                    .show()
            }
        }

    }

    companion object {
        fun newInstance() = HistoryFragment()
    }
}