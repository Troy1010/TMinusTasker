package com.tminus1010.tminustasker.environment.adapter

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

/**
 * This moshi provider is global, but it cannot depend on something like a DAO. You will need a different pattern for that.
 */
object MoshiProvider {
    val moshi =
        Moshi.Builder()
            .add(PairAdapterFactory)
            .add(TripleAdapterFactory)
            .add(BigDecimalAdapter)
            .addLast(KotlinJsonAdapterFactory())
            .build()
}