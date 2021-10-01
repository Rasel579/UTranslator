package com.professional.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.professional.databinding.FavoriteFragmentBinding
import com.test_app.model.AppState
import com.professional.ui.base.BaseFragment
import com.professional.viewmodels.FavoriteViewModel
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class FavoriteFragment : BaseFragment() {
    override val viewModel: FavoriteViewModel by inject(named<FavoriteViewModel>())
    private val viewBinding: FavoriteFragmentBinding by viewBinding(CreateMethod.INFLATE)

    override fun renderData(appState: com.test_app.model.AppState) {
        when (appState) {
            is com.test_app.model.AppState.SuccessFavorite -> {
                viewBinding.favoriteRecycleView.adapter = FavoriteAdapter(appState.data)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it)})
        viewModel.getData()
    }

    companion object {
        fun newInstance() = FavoriteFragment()
    }
}