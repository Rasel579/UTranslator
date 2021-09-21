package com.professional.ui.mainfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.professional.databinding.ItemTranslateLayoutBinding
import com.professional.models.data.TranslationDataItem

class Adapter(
    private val data: ArrayList<TranslationDataItem>
) : RecyclerView.Adapter<Adapter.ItemHolder>() {
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
        fun bind(dataItem: TranslationDataItem) {
            binding.engText.text = dataItem.text
            binding.transcription.text = dataItem.meanings.first().transcription
            binding.translationText.text = dataItem.meanings.joinToString{
                it.translation.text
            }
        }
    }
}