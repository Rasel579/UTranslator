package com.test_app.favoritefeature.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test_app.favoritefeature.databinding.ItemFavoriteLayoutBinding
import com.test_app.model.data.entity.FavoriteDataEntity

class FavoriteAdapter(
    private val data: List<FavoriteDataEntity>
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
        fun bind(dataEntity: FavoriteDataEntity) {
            binding.title.text = dataEntity.word
            binding.translation.text = dataEntity.translation
        }

    }

}
