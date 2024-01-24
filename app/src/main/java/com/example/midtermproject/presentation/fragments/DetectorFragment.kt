package com.example.midtermproject.presentation.fragments

import android.opengl.Visibility
import android.text.Editable
import android.text.TextWatcher
import android.util.Log.d
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.midtermproject.data.common.Resource
import com.example.midtermproject.databinding.FragmentDetectorBinding
import com.example.midtermproject.presentation.adapters.ChosenSymptomsRecyclerAdapter
import com.example.midtermproject.presentation.adapters.IssuesRecyclerAdapter
import com.example.midtermproject.presentation.adapters.RemindersRecyclerAdapter
import com.example.midtermproject.presentation.adapters.SymptomsRecyclerAdapter
import com.example.midtermproject.presentation.base.BaseFragment
import com.example.midtermproject.presentation.mapper.SymptomUI
import com.example.midtermproject.presentation.viewModels.DetectorViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetectorFragment : BaseFragment<FragmentDetectorBinding>(FragmentDetectorBinding::inflate) {

    private val viewModel: DetectorViewModel by viewModels()
    private lateinit var symptomsRecyclerAdapter: SymptomsRecyclerAdapter
    private lateinit var chosenSymptomsRecyclerAdapter: ChosenSymptomsRecyclerAdapter
    private lateinit var issuesRecyclerAdapter: IssuesRecyclerAdapter
    private lateinit var initialSymptoms: List<SymptomUI>
    private var chosenSymptoms = mutableSetOf<SymptomUI>()


    override fun setUp() {
        recyclerInit()
        viewModel.getSymptoms()
        bindObserves()
    }

    override fun listeners() {

        binding.etSearch.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.recyclerViewSymptoms.visibility = View.VISIBLE
            } else {
                binding.recyclerViewSymptoms.visibility = View.GONE
            }
        }
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                binding.recyclerViewSymptoms.visibility = View.VISIBLE
            }

            override fun onTextChanged(
                charSequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                val query = charSequence?.toString()?.trim() ?: ""
                if (query.isEmpty()) {

                    symptomsRecyclerAdapter.submitList(initialSymptoms)
                } else {
                    symptomsRecyclerAdapter.filter(query)
                }
            }

            override fun afterTextChanged(editable: Editable?) {


            }
        })


        binding.btnDetect.setOnClickListener {
            if (!binding.etAge.text.isNullOrEmpty() && chosenSymptoms.isNotEmpty()) {
                viewModel.getDiagnosis(

                    chosenSymptoms.map { it.id }.toList(),
                    genderChecker(),
                    binding.etAge.text.toString()

                )
                observeIssues()
                binding.apply {
                    recyclerChosenSymptoms.visibility = View.GONE
                    btnDetect.visibility = View.GONE
                    btnTryAgain.visibility = View.VISIBLE
                    recyclerIssues.visibility = View.VISIBLE
                    recyclerViewSymptoms.visibility = View.GONE
                    etSearch.visibility = View.GONE
                }
            } else {
                Toast.makeText(requireContext(), "Fill all the information", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        binding.btnTryAgain.setOnClickListener {
            chosenSymptoms.clear()
            chosenSymptomsRecyclerAdapter.submitList(chosenSymptoms.toList())
            binding.apply {
                recyclerChosenSymptoms.visibility = View.VISIBLE
                btnDetect.visibility = View.VISIBLE
                tvNotFound.visibility = View.GONE
                recyclerIssues.visibility = View.GONE
                etSearch.visibility = View.VISIBLE
            }
            it.visibility = View.GONE


        }
    }

    private fun recyclerInit() {
        symptomsRecyclerAdapter = SymptomsRecyclerAdapter {
            chosenSymptoms.add(it)
            chosenSymptomsRecyclerAdapter.submitList(chosenSymptoms.toList())
        }

        binding.apply {
            recyclerViewSymptoms.layoutManager = LinearLayoutManager(requireContext())

            recyclerViewSymptoms.adapter = symptomsRecyclerAdapter
        }

        chosenSymptomsRecyclerAdapter = ChosenSymptomsRecyclerAdapter {
            chosenSymptoms.remove(it)
            chosenSymptomsRecyclerAdapter.submitList(chosenSymptoms.toList())
        }

        binding.apply {
            recyclerChosenSymptoms.layoutManager = GridLayoutManager(requireContext(), 3)
            recyclerChosenSymptoms.adapter = chosenSymptomsRecyclerAdapter
        }

        issuesRecyclerAdapter = IssuesRecyclerAdapter()

        binding.apply {
            recyclerIssues.layoutManager = LinearLayoutManager(requireContext())
            recyclerIssues.adapter = issuesRecyclerAdapter
        }


    }

    private fun observeIssues() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.issueListFlow.collect {
                    when (it) {
                        is Resource.Loading -> {
                            binding.pbDetector.visibility = View.VISIBLE
                        }

                        is Resource.Success -> {
                            val initialIssues = it.responseData
                            issuesRecyclerAdapter.submitList(initialIssues)
                            binding.pbDetector.visibility = View.GONE
                            if(initialIssues.isEmpty()){
                                binding.tvNotFound.visibility = View.VISIBLE
                            }

                        }

                        is Resource.Failed -> {
                            val errorMessage = it.message
                            binding.pbDetector.visibility = View.GONE

                        }
                    }
                }
            }
        }
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.symptomListFlow.collect {
                    when (it) {
                        is Resource.Loading -> {

                        }

                        is Resource.Success -> {
                            initialSymptoms = it.responseData
                            symptomsRecyclerAdapter.submitList(initialSymptoms)


                        }

                        is Resource.Failed -> {
                            val errorMessage = it.message
                            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT)
                                .show()
                            d("framgnetFailed", "fail")

                        }
                    }
                }
            }
        }
    }

    private fun genderChecker(): String {
        return if (binding.rgMale.isChecked) {
            "male"
        } else {
            "female"
        }
    }
}