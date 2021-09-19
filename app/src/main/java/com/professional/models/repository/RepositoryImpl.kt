package com.professional.models.repository

import com.professional.models.cloud.CloudSource
import com.professional.models.data.TranslationDataItem
import com.professional.rxschedulers.Schedulers
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val cloud : CloudSource,
    private val schedulers: Schedulers
) : Repository{
    override fun getData(word: String): Single<ArrayList<TranslationDataItem>> =
        cloud.getData(word).subscribeOn(schedulers.io())
}