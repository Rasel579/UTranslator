package com.test_app.descriptionfeature.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.test_app.core.baseui.BaseFragment
import com.test_app.descriptionfeature.databinding.DescriptionFragmentBinding
import com.test_app.descriptionfeature.viewmodel.DescriptionViewModel
import com.test_app.model.AppState
import com.test_app.model.data.TranslationDataItem
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

class DescriptionFragment : BaseFragment(), AndroidScopeComponent {
    override val scope: Scope by fragmentScope()
    override val viewModel: DescriptionViewModel by viewModel(named<DescriptionViewModel>())

    private val word by lazy {
        arguments?.getString(ARG_STRING)
    }

    private val viewBinding: DescriptionFragmentBinding by viewBinding(CreateMethod.INFLATE)

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessDescription -> {
                initData(appState.data)
            }
        }
    }

    private fun initData(data: TranslationDataItem) = with(viewBinding) {
        title.text = data.text
        transcription.text = data.meanings.joinToString {
            it.transcription
        }
        translationText.text = data.meanings.joinToString {
            it.translation.text
        }
        translationNote.text = data.meanings.joinToString {
            it.translation.note
        }
        progressCircular.visibility = View.VISIBLE
        imageLoader.load(
            url = "https:${data.meanings.first().imageUrl}",
            imageView = imgWord,
            root = viewBinding.root,
            progressBar = progressCircular
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it) }
        word?.let { viewModel.getData(it) }
    }

    companion object {
        private const val ARG_STRING = "arg_string"
        fun newInstance(word: String) = DescriptionFragment().apply {
            arguments = bundleOf(ARG_STRING to word)
        }
    }
}