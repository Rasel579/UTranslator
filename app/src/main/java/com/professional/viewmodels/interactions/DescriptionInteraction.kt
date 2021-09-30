package com.professional.viewmodels.interactions

import com.professional.models.AppState
import com.professional.models.data.TranslationDataItem
import com.professional.models.repository.Repository

class DescriptionInteraction(
    private val repo: Repository
) : Interaction {
    override suspend fun getData(word: String): AppState =
        AppState.SuccessDescription(repo.getDataByWordFromDb(word))


    override suspend fun getHistoryData(): AppState =
        AppState.Success(repo.getHistoryData())

    override suspend fun saveToFavorite(item: TranslationDataItem) {
        TODO("Not yet implemented")
    }

}