package com.professional.models.repository

import com.professional.models.data.TranslationDataItem
import io.reactivex.rxjava3.core.Single

interface Repository {
    fun getData(word : String) : Single<ArrayList<TranslationDataItem>>
}