package com.tminus1010.tminustasker.data

import com.tminus1010.tminustasker.data.easy_data_store.EasyDataStore
import com.tminus1010.tminustasker.data.easy_data_store.EasyDataStoreFactory
import com.tminus1010.tminustasker.data.easy_data_store.create
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SelectedHostPageRepo @Inject constructor(
    easyDataStoreFactory: EasyDataStoreFactory,
) : EasyDataStore<Int?> by easyDataStoreFactory.create(null)