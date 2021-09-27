package com.professional.viewmodels.interactions

import com.professional.models.AppState

interface Interaction {
    suspend fun getData(word: String): AppState
}