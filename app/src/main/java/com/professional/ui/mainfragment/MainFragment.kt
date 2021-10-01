package com.professional.ui.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.professional.R
import com.professional.databinding.FragmentMainBinding
import com.test_app.core.baseui.BaseFragment
import com.test_app.descriptionfeature.ui.DescriptionFragment
import com.test_app.favoritefeature.ui.FavoriteFragment
import com.professional.viewmodels.MainViewModel
import com.test_app.historyfeature.ui.HistoryFragment
import com.test_app.model.AppState
import com.test_app.model.data.TranslationDataItem
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class MainFragment : BaseFragment() {
    private val viewBinding: FragmentMainBinding by viewBinding(CreateMethod.INFLATE)

    private var adapter: Adapter? = null

    override val viewModel: MainViewModel by inject(named<MainViewModel>())

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Loading -> viewBinding.progressCircular.visibility = View.VISIBLE
            is AppState.Success -> {
                adapter = Adapter(
                    appState.data,
                    object : Adapter.OnItemClick {
                        override fun changeFrg(word: String) {
                            showFragment(DescriptionFragment.newInstance(word), R.id.container)
                        }

                        override fun saveToFavorite(dataItem: TranslationDataItem) {
                            viewModel.saveToFavorite(dataItem)
                        }


                    }
                )
                viewBinding.recycleView.adapter = adapter
                viewBinding.progressCircular.visibility = View.GONE
            }
            is AppState.Error -> appState.error.message?.let {
                Snackbar
                    .make(viewBinding.root, it, Snackbar.LENGTH_LONG)
                    .show()
            }
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


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.history_menu -> {
                HistoryFragment
                    .newInstance()
                    .show(childFragmentManager, TAG)
            }
            R.id.favorite_menu -> {
                showFragment(FavoriteFragment.newInstance(), R.id.container)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    companion object {
        private const val TAG = "tag"
        fun newInstance() = MainFragment()
    }
}