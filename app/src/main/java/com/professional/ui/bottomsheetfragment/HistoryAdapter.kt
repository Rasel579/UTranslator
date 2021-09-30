package com.professional.ui.bottomsheetfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.professional.databinding.ItemTranslateLayoutBinding
import com.professional.models.data.TranslationDataItem

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