package com.tminus1010.tminustasker.ui.dashboard

import android.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tminus1010.tmcommonkotlin.androidx.ShowToast
import com.tminus1010.tmcommonkotlin.view.NativeText
import com.tminus1010.tminustasker.R
import com.tminus1010.tminustasker.domain.CategoryInfo
import com.tminus1010.tminustasker.domain.MainInteractor
import com.tminus1010.tminustasker.ui.all_features.ComposableNativeText
import com.tminus1010.tminustasker.ui.all_features.vm_item.MenuVMItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val mainInteractor: MainInteractor,
    private val showToast: ShowToast,
) : ViewModel() {
    fun userSelectCategory(categoryInfo: CategoryInfo) {
        viewModelScope.launch {
            showToast(NativeText.Arguments(R.string.registered_task_completion_for, categoryInfo.categoryName))
            mainInteractor.registerTaskCompletionForCategory(categoryInfo.categoryName)
        }
    }

    fun userRemoveCategory(categoryInfo: CategoryInfo) {
        viewModelScope.launch {
            showToast(NativeText.Arguments(R.string.removed_category, categoryInfo.categoryName))
            mainInteractor.removeCategory(categoryInfo.categoryName)
        }
    }


    val state =
        mainInteractor.categories
            .map { categories ->
                categories.map {
                    CategoryViewModelItem(
                        categoryName = it.categoryName,
                        backgroundColor = if (it.isCompletedToday) Color.GREEN else 0xFFA52A2A.toInt(),
                        textColor = if (it.isCompletedToday) Color.BLACK else 0xFFF5F5F5.toInt(),
                        menuVMItems = listOf(
                            MenuVMItem(
                                text = ComposableNativeText.Simple("Remove Category"),
                                onClick = { userRemoveCategory(it) }
                            )
                        ),
                        onClick = { userSelectCategory(it) }
                    )
                }
            }
            .stateIn(viewModelScope, SharingStarted.Eagerly, listOf())
}