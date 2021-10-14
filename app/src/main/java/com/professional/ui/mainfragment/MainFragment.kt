package com.professional.ui.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.snackbar.Snackbar
import com.professional.R
import com.professional.databinding.FragmentMainBinding
import com.professional.viewmodels.MainViewModel
import com.test_app.core.baseui.BaseFragment
import com.test_app.descriptionfeature.ui.DescriptionFragment
import com.test_app.favoritefeature.ui.FavoriteFragment
import com.test_app.historyfeature.ui.HistoryFragment
import com.test_app.model.AppState
import com.test_app.model.data.TranslationDataItem
import com.test_app.utils.views.getViewById
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class MainFragment : BaseFragment(), AndroidScopeComponent {
    private val viewBinding: FragmentMainBinding by viewBinding(CreateMethod.INFLATE)
    private val progressBar by getViewById<CircularProgressIndicator>(R.id.progress_circular)
    override val scope by fragmentScope()

    override val viewModel: MainViewModel by viewModel(named<MainViewModel>())

    private var adapter: Adapter? = null


    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Loading -> progressBar.visibility = View.VISIBLE
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
                viewBinding.recycleView.layoutManager =
                    LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                progressBar.visibility = View.GONE
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
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        viewBinding.translateBtn.setOnClickListener {
            viewModel.getData(viewBinding.editText.text.toString())
        }
        return viewBinding.root
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