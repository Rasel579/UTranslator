package com.professional.ui.mainfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.professional.R
import com.professional.databinding.ItemTranslateLayoutBinding
import com.test_app.model.data.TranslationDataItem
import com.test_app.utils.views.getViewById

class Adapter(
    private val data: List<TranslationDataItem>,
    private val itemClickListener: OnItemClick
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
        private val engTextView by getViewById<MaterialTextView>(R.id.eng_text)
        fun bind(dataItem: TranslationDataItem) = with(binding) {
            engTextView.text = dataItem.text
            transcription.text = dataItem.meanings.first().transcription
            translationText.text = dataItem.meanings.joinToString {
                it.translation.text
            }
            binding.root.setOnClickListener {
                itemClickListener.changeFrg(binding.engText.text.toString())
            }
            binding.favoriteBtn.setOnClickListener {
                itemClickListener.saveToFavorite(dataItem)
            }
        }
    }

    interface OnItemClick {
        fun changeFrg(word: String)
        fun saveToFavorite(dataItem: TranslationDataItem)
    }
}