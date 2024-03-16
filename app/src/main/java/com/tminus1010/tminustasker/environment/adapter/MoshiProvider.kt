package com.tminus1010.tminustasker.environment.adapter

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

// This moshi provider is global, but it cannot depend on something like a DAO. You will need a different pattern for that.
// I am leaving it like this for now because I might want to use it in a Room adapter, and I am not sure if the latest Room version handles dependencies nicely.
object MoshiProvider {
    val moshi =
        Moshi.Builder()
            .add(PairAdapterFactory)
            .add(TripleAdapterFactory)
            .add(BigDecimalAdapter)
            .add(LocalDateAdapter)
            .addLast(KotlinJsonAdapterFactory())
            .build()
}