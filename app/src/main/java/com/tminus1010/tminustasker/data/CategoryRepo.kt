package com.tminus1010.tminustasker.data

import com.tminus1010.tminustasker.data.easy_data_store.EasyDataStore
import com.tminus1010.tminustasker.data.easy_data_store.EasyDataStoreFactory
import com.tminus1010.tminustasker.data.easy_data_store.create
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class CategoryRepo @Inject constructor(
    easyDataStoreFactory: EasyDataStoreFactory,
) : EasyDataStore<List<String>> by easyDataStoreFactory.create(listOf()) {
    suspend fun add(categoryName: String) {
        set(flow.first().plus(categoryName)) // TODO: Still need to make this robust against race conditions.
    }

    suspend fun remove(categoryName: String) {
        set(flow.first().minus(categoryName)) // TODO: Still need to make this robust against race conditions.
    }
}