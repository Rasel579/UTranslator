package com.professional.ui.description

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.professional.App
import com.professional.databinding.DescriptionFragmentBinding
import com.professional.models.AppState
import com.professional.models.data.TranslationDataItem
import com.professional.ui.base.BaseFragment
import com.professional.viewmodels.DescriptionViewModel
import com.professional.viewmodels.base.BaseViewModel
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class DescriptionFragment : BaseFragment() {
    private val viewBinding: DescriptionFragmentBinding by viewBinding(CreateMethod.INFLATE)
    private val word by lazy {
        arguments?.getString(ARG_STRING)
    }

    override val viewModel by inject<DescriptionViewModel>(named<DescriptionViewModel>())

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessDescription -> {
                initData(appState.data)
                Log.e("appstate_view", appState.data.toString())
            }
        }
    }

    private fun initData(data: TranslationDataItem)= with(viewBinding) {
        Log.e("data", data.toString())
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
        imageLoader.load("https:${data.meanings.first().imageUrl}", imgWord)
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