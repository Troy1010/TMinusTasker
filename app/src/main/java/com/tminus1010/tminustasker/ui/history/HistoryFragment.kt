package com.tminus1010.tminustasker.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tminus1010.tminustasker.R
import com.tminus1010.tminustasker.all_layers.extensions.getColorByAttr
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
//                val state = viewModel.state.collectAsStateWithLifecycle()
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(requireContext().getColorByAttr(R.attr.colorBackgroundWorkaround)) // TODO: Use Material instead.,
                ) {
                    Text(
                        text = "History Fragment",
                        modifier = Modifier
                            .align(Alignment.Center),
                        color = requireContext().getColorByAttr(com.google.android.material.R.attr.colorOnBackground), // TODO: Use Material instead.
                    )
                }
            }
        }
    }
}