package com.professional.models.cloud

import com.professional.models.data.TranslationDataItem
import io.reactivex.rxjava3.core.Single

interface CloudSource {
    fun getData(word : String) : Single<ArrayList<TranslationDataItem>>
}