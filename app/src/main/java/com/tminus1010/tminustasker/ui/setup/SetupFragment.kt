package com.tminus1010.tminustasker.ui.setup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.tminus1010.tminustasker.databinding.FragmentSetupBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


@AndroidEntryPoint
class SetupFragment : Fragment() {

    private var _binding: FragmentSetupBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy { ViewModelProvider(this).get(SetupViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSetupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // # User Intents
        binding.edittextInputCategory.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                runBlocking { viewModel.userInput.emit(binding.edittextInputCategory.text.toString()) }
                getSystemService(requireActivity(), InputMethodManager::class.java)?.hideSoftInputFromWindow(view.windowToken, 0)
                return@OnEditorActionListener true
            }
            false
        })
        binding.buttonAddCategory.setOnClickListener { lifecycleScope.launch { viewModel.userAddCategory() } }
        binding.buttonPlayground.setOnClickListener { lifecycleScope.launch { viewModel.userPlayground() } }
        binding.buttonClearCategories.setOnClickListener { lifecycleScope.launch { viewModel.userClearCategories() } }
        binding.buttonClearTaskCompletions.setOnClickListener { lifecycleScope.launch { viewModel.userClearTaskCompletions() } }
        binding.buttonClearTaskCompletionsForToday.setOnClickListener { lifecycleScope.launch { viewModel.userClearTaskCompletionsToday() } }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}