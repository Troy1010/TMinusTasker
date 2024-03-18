package com.tminus1010.tminustasker.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tminus1010.tminustasker.R
import com.tminus1010.tminustasker.all_layers.extensions.colorByAttr
import com.tminus1010.tminustasker.domain.model.TaskCompletion
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HistoryFragment : Fragment() {
    private val viewModel by lazy { ViewModelProvider(this).get(HistoryViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val state = viewModel.state.collectAsStateWithLifecycle().value
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorByAttr(R.attr.colorBackgroundWorkaround)), // TODO: Setup Compose+Material theming
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        state.keys.forEach { key ->
                            Text(
                                text = key,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(start = 32.dp, bottom = 16.dp),
                                color = colorByAttr(com.google.android.material.R.attr.colorOnBackground), // TODO: Setup Compose+Material theming
                            )
                            state[key]?.forEach { taskCompletion: TaskCompletion ->
                                Text(
                                    text = "${taskCompletion.categoryName}: ${taskCompletion.message ?: "<No message>"}",
                                    modifier = Modifier
                                        .padding(vertical = 4.dp),
                                    color = colorByAttr(com.google.android.material.R.attr.colorOnBackground), // TODO: Setup Compose+Material theming
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}