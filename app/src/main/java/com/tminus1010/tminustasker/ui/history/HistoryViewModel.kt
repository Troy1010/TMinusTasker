package com.tminus1010.tminustasker.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tminus1010.tminustasker.domain.LocalDateTimeToLocalDateWithAdjustmentConverter
import com.tminus1010.tminustasker.domain.MainInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val mainInteractor: MainInteractor,
    private val localDateTimeToLocalDateWithAdjustmentConverter: LocalDateTimeToLocalDateWithAdjustmentConverter,
) : ViewModel() {
    val state =
        mainInteractor.taskCompletions
            .map { taskCompletions ->
                taskCompletions
                    .groupBy { localDateTimeToLocalDateWithAdjustmentConverter.convert(it.localDateTime) }
                    .mapKeys { (key, value) -> "$key${if (key == localDateTimeToLocalDateWithAdjustmentConverter.convert(LocalDateTime.now())) " (Today)" else ""}" } // TODO: Make Reactive
            }
            .stateIn(viewModelScope, SharingStarted.Eagerly, mapOf())
}