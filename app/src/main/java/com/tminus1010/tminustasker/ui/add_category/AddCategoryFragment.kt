package com.tminus1010.tminustasker.ui.add_category

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
import com.tminus1010.tmcommonkotlin.coroutines.extensions.observe
import com.tminus1010.tmcommonkotlin.view.extensions.easyToast
import com.tminus1010.tminustasker.R
import com.tminus1010.tminustasker.databinding.FragmentAddCategoryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


@AndroidEntryPoint
class AddCategoryFragment : Fragment() {

    private var _binding: FragmentAddCategoryBinding? = null
    private val binding get() = _binding!!
    val viewModel by lazy { ViewModelProvider(this).get(AddCategoryViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // # Events
        viewModel.toastSuccessfullySubmitted.observe(this) { easyToast(getString(R.string.submitted_successfully)) }
        // # User Intents
        binding.edittextInputCategory.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                runBlocking { viewModel.userInputCategoryName.emit(binding.edittextInputCategory.text.toString()) }
                getSystemService(requireActivity(), InputMethodManager::class.java)?.hideSoftInputFromWindow(view.windowToken, 0)
                return@OnEditorActionListener true
            }
            false
        })
        binding.buttonSubmit.setOnClickListener { runBlocking { viewModel.userSubmit() } }
        binding.buttonPlayground.setOnClickListener { lifecycleScope.launch { viewModel.userPlayground() } }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}