package com.professional.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.professional.databinding.ItemFavoriteLayoutBinding
import com.test_app.model.data.entity.FavoriteDataEntity

class FavoriteAdapter(
    private val data: List<com.test_app.model.data.entity.FavoriteDataEntity>
) : RecyclerView.Adapter<FavoriteAdapter.ItemFavView>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemFavView =
        ItemFavView(
            ItemFavoriteLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ItemFavView, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ItemFavView(
        private val binding: ItemFavoriteLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataEntity: com.test_app.model.data.entity.FavoriteDataEntity) {
            binding.title.text = dataEntity.word
            binding.translation.text = dataEntity.translation
        }

    }

}
