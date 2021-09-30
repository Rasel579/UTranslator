package com.professional.viewmodels.interactions

import com.professional.models.AppState
import com.professional.models.data.TranslationDataItem
import com.professional.models.repository.Repository

class FavoriteInteraction(
    private val repo: Repository
) : Interaction {
    override suspend fun getData(word: String): AppState {
        TODO("Not yet implemented")
    }

    override suspend fun getHistoryData(): AppState =
        AppState.SuccessFavorite(repo.getFavorite())

    override suspend fun saveToFavorite(item: TranslationDataItem) {
        TODO("Not yet implemented")
    }


}