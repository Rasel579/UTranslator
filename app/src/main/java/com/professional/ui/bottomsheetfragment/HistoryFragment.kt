package com.professional.ui.bottomsheetfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.professional.databinding.HistoryFragmentBinding
import com.test_app.model.AppState
import com.professional.viewmodels.HistoryViewModel
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class HistoryFragment : BottomSheetDialogFragment() {
    private val viewBinding: HistoryFragmentBinding by viewBinding(CreateMethod.INFLATE)
    private val viewModel: HistoryViewModel by inject(named<HistoryViewModel>())
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

    private fun renderData(appState: com.test_app.model.AppState) {
        when (appState) {
            is com.test_app.model.AppState.Success -> {
                viewBinding.historyRecycleView.adapter = HistoryAdapter(appState.data)
            }
            is com.test_app.model.AppState.Error -> appState.error.message?.let {
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