package com.tminus1010.tminustasker.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tminus1010.tmcommonkotlin.androidx.ShowToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    val viewModel by lazy { ViewModelProvider(this).get(DashboardViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val state = viewModel.state.collectAsStateWithLifecycle()
                ComposableList(state.value, ShowToast(this@DashboardFragment.requireActivity().application))
            }
        }
    }
}

@Composable
fun ComposableList(items: List<CategoryViewModelItem>, showToast: ShowToast) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        items.forEach { item ->
            var expanded by remember { mutableStateOf(false) }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onLongPress = {
                                logz("long click")
                                showToast("long click")
                                expanded = true
                            },
                            onTap = {
                                logz("normal click")
                                showToast("normal click")
                                item.onClick()
                            }
                        )
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color(item.backgroundColor)
                ),
            ) {
                Text(
                    text = item.categoryName,
                    color = Color(item.textColor),
                    modifier = Modifier.padding(16.dp),
                    fontSize = 16.sp,
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    item.menuVMItems.forEach { menuVMItem ->
                        DropdownMenuItem(
                            text = { Text(menuVMItem.text.stringResource()) },
                            onClick = { menuVMItem.onClick() }
                        )
                    }
                }
            }
        }
    }
}
