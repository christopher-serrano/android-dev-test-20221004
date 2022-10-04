package com.example.apptest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apptest.databinding.FragmentHomeBinding
import com.example.apptest.ui.activity.BaseFragment
import com.example.apptest.ui.adapter.base.DynamicAdapter
import com.example.apptest.ui.adapter.base.ItemModel
import com.example.apptest.ui.adapter.item.model.JokeItemModel
import com.example.apptest.ui.adapter.item.model.JokeTitleItemModel
import com.example.apptest.ui.adapter.type.factory.JokeTypeFactoryImpl
import com.example.apptest.ui.viewmodel.JokeViewModel
import com.example.apptest.utils.toastLong
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class HomeFragment : BaseFragment(), KoinComponent {

    private val viewmodel: JokeViewModel by viewModel()

    private var categoryList: List<String>? = null
    private lateinit var mutableCategoryList: MutableList<ItemModel>

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var jokeCategoryAdapter: DynamicAdapter? = null

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

        mutableCategoryList = mutableListOf()
        categoryList?.forEach {
            mutableCategoryList.add(JokeTitleItemModel(it))
        }

        jokeCategoryAdapter = DynamicAdapter(
            typeFactory = JokeTypeFactoryImpl(),
            onClick = onItemClick
        )

        jokeCategoryAdapter?.submitList(mutableCategoryList)

        binding.rvCategories.adapter = jokeCategoryAdapter
        binding.rvCategories.layoutManager = LinearLayoutManager(context)

    }

    // Callback for onClick event
    private var onItemClick: (ItemModel, String) -> Unit = { item, action ->
        // TODO: make this leaner
        val servantModel: JokeTitleItemModel = item as JokeTitleItemModel
        when (action) {
            "ACTION_GO" -> {
                val category = item.model
                requireActivity().toastLong("Joke Category -> $category")
            }
            else -> {
                requireActivity().toastLong("ELSE!")
            }
        }
    }

}