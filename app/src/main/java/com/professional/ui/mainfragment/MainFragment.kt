package com.professional.ui.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.professional.databinding.FragmentMainBinding
import com.professional.models.AppState
import com.professional.ui.base.BaseFragment
import com.professional.viewmodels.base.BaseViewModel
import org.koin.android.ext.android.inject

class MainFragment : BaseFragment() {
    private val viewBinding: FragmentMainBinding by viewBinding(CreateMethod.INFLATE)

    private var adapter: Adapter? = null

    override val viewModel: BaseViewModel by inject<BaseViewModel>()

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Loading -> viewBinding.progressCircular.visibility = View.VISIBLE
            is AppState.Success -> {
                adapter = Adapter(appState.data)
                viewBinding.recycleView.adapter = adapter
                viewBinding.progressCircular.visibility = View.GONE
            }
            is AppState.Error -> Snackbar
                .make(viewBinding.root, appState.error.localizedMessage, Snackbar.LENGTH_LONG)
                .show()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        viewBinding.translateBtn.setOnClickListener {
            viewModel.getData(viewBinding.editText.text.toString())
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}