package com.professional.ui.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.professional.databinding.FragmentMainBinding
import com.professional.models.AppState
import com.professional.presentors.Interaction
import com.professional.presentors.MainPresenter
import com.professional.presentors.Presenter
import com.professional.rxschedulers.Schedulers
import com.professional.ui.base.BaseFragment
import javax.inject.Inject

class MainFragment : BaseFragment() {
    private val viewBinding: FragmentMainBinding by viewBinding(CreateMethod.INFLATE)

    private var adapter: Adapter? = null

    @Inject
    lateinit var interaction: Interaction

    @Inject
    lateinit var schedulers: Schedulers
    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Loading -> viewBinding.progressCircular.visibility = View.VISIBLE
            is AppState.Success -> {
                adapter = Adapter(appState.data)
                viewBinding.recycleView.adapter = adapter
                viewBinding.progressCircular.visibility = View.GONE
            }
        }
    }

    override fun createPresenter(): Presenter<com.professional.views.View, AppState> =
        MainPresenter(interaction, schedulers)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.translateBtn.setOnClickListener {
            presenter.getData(viewBinding.editText.text.toString())
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}