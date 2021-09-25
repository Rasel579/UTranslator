package com.professional.models.cloud

import com.professional.models.cloud.api.ServiceApi
import com.professional.models.data.TranslationDataItem
import com.professional.rxschedulers.Schedulers
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CloudImpl(
    private val api: ServiceApi,
    private val schedulers: Schedulers
) : CloudSource{
    override fun getData(word: String): Single<ArrayList<TranslationDataItem>> =
        api.getTranslation(word)
            .subscribeOn(schedulers.io())
}