package com.test_app.descriptionfeature.interaction

import com.test_app.core.interaction.Interaction
import com.test_app.model.AppState
import com.test_app.model.data.TranslationDataItem
import com.test_app.repository.Repository

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