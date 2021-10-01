package com.test_app.historyfeature.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test_app.historyfeature.databinding.ItemTranslateLayoutBinding
import com.test_app.model.data.TranslationDataItem

class HistoryAdapter(
    private val data: List<TranslationDataItem>,
) : RecyclerView.Adapter<HistoryAdapter.ItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder =
        ItemHolder(
            ItemTranslateLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ItemHolder(
        private val binding: ItemTranslateLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataItem: TranslationDataItem) = with(binding) {
            engText.text = dataItem.text
            transcription.text = dataItem.meanings.first().transcription
            translationText.text = dataItem.meanings.joinToString {
                it.translation.text
            }

        }
    }
}