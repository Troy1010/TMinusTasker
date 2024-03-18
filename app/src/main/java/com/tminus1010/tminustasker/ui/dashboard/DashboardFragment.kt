package com.tminus1010.tminustasker.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.tminus1010.tmcommonkotlin.androidx.extensions.getColorByAttr
import com.tminus1010.tminustasker.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private val viewModel by lazy { ViewModelProvider(this).get(DashboardViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val state = viewModel.state.collectAsStateWithLifecycle()
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(requireContext().theme.getColorByAttr(R.attr.colorBackgroundWorkaround))) // TODO: Use Material instead. Not using it for background atm because it is messing with bottom nav bar button click animation.
                ) {
                    ComposableList(state.value)
                }
            }
        }
    }
}

@Composable
fun ComposableList(categoryViewModelItems: List<CategoryViewModelItem>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        categoryViewModelItems.forEach { categoryViewModelItem ->
            var expanded by remember { mutableStateOf(false) }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onLongPress = { expanded = true },
                            onTap = { categoryViewModelItem.onClick() },
                        )
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color(categoryViewModelItem.backgroundColor)
                ),
            ) {
                Column {
                    Text(
                        text = categoryViewModelItem.categoryName,
                        color = Color(categoryViewModelItem.textColor),
                        modifier = Modifier.padding(16.dp),
                        fontSize = 16.sp,
                    )
                    categoryViewModelItem.todaysCompletionMessages.forEach { taskCompletionMessage ->
                        Text(
                            text = taskCompletionMessage ?: "<No message>",
                            color = Color(categoryViewModelItem.textColor),
                            modifier = Modifier.padding(horizontal = 32.dp),
                            fontSize = 14.sp,
                        )
                    }
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    categoryViewModelItem.menuVMItems.forEach { menuVMItem ->
                        DropdownMenuItem(
                            text = { Text(menuVMItem.text.stringResource()) },
                            onClick = { menuVMItem.onClick(); expanded = false },
                        )
                    }
                }
                if (categoryViewModelItem.todaysCompletionMessages.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}
