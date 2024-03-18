package com.tminus1010.tminustasker.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tminus1010.tmcommonkotlin.tuple.tuple
import com.tminus1010.tminustasker.data.CurrentDateRepo
import com.tminus1010.tminustasker.domain.LocalDateTimeToLocalDateWithAdjustmentConverter
import com.tminus1010.tminustasker.domain.MainInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val mainInteractor: MainInteractor,
    private val currentDateRepo: CurrentDateRepo,
    private val localDateTimeToLocalDateWithAdjustmentConverter: LocalDateTimeToLocalDateWithAdjustmentConverter,
) : ViewModel() {
    val state =
        combine(mainInteractor.taskCompletions, currentDateRepo.flow, ::tuple)
            .map { (taskCompletions, currentDate) ->
                taskCompletions
                    .groupBy { localDateTimeToLocalDateWithAdjustmentConverter.convert(it.localDateTime) }
                    .mapKeys { (key, value) -> "$key${if (key == currentDate) " (Today)" else ""}" }
            }
            .stateIn(viewModelScope, SharingStarted.Eagerly, mapOf())
}