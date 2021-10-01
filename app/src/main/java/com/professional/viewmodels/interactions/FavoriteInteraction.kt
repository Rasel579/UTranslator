package com.professional.viewmodels.interactions

import com.test_app.repository.Repository

class FavoriteInteraction(
    private val repo: Repository
) : Interaction {
    override suspend fun getData(word: String): com.test_app.model.AppState {
        TODO("Not yet implemented")
    }

    override suspend fun getHistoryData(): com.test_app.model.AppState =
        com.test_app.model.AppState.SuccessFavorite(repo.getFavorite())

    override suspend fun saveToFavorite(item: com.test_app.model.data.TranslationDataItem) {
        TODO("Not yet implemented")
    }


}