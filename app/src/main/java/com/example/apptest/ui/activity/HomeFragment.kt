package com.example.apptest.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.apptest.R
import com.example.apptest.databinding.FragmentHomeBinding
import com.example.apptest.ui.viewmodel.JokeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class HomeFragment : BaseFragment(), KoinComponent {

    private val viewmodel: JokeViewModel by viewModel()

    private var categoryList: List<String>? = null

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loadData()
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel() = viewmodel.run {
        jokeObj.observe(viewLifecycleOwner) {

        }
        categoryListObj.observe(viewLifecycleOwner) { list ->
            list?.let {
                categoryList = it
                setUpDynamicAdapter()
            }
        }
    }

    private fun loadData() {
        viewmodel.getCategoryList()
    }

    override fun setUpDynamicAdapter() {
        super.setUpDynamicAdapter()
    }
}