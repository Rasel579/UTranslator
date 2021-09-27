package com.professional.viewmodels.interactions

import android.content.Context
import android.os.Build
import android.os.Environment
import android.util.Log
import androidx.core.net.toUri
import com.professional.models.AppState
import com.professional.models.data.TranslationDataItem
import com.professional.models.repository.Repository
import com.professional.utils.NetworkStatus
import java.io.File
import java.io.FileWriter

class MainInteraction(
    private val repo: Repository,
    private val networkStatus: NetworkStatus
) : Interaction {
    override suspend fun getData(word: String): AppState {
        return if (networkStatus.isOnline()) {
            AppState.Success(repo.getData(word))
        } else {
            AppState.Error(Throwable(ERROR_MSG))
        }
    }


    companion object {
        private const val ERROR_MSG = "Error Internet connection"
    }
}

