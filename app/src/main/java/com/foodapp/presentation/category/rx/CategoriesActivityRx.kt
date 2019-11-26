package com.foodapp.presentation.category.rx

import android.os.Bundle
import androidx.lifecycle.Observer
import com.foodapp.R
import com.foodapp.databinding.ActivityCategoriesBinding
import com.foodapp.databinding.ActivityCategoriesRxBinding
import com.foodapp.presentation.base.BaseActivity
import com.foodapp.presentation.category.CategoryAdapter
import com.foodapp.presentation.category.coroutines.CategoryViewModel
import com.foodapp.presentation.ui.extensions.verticalAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoriesActivityRx : BaseActivity<ActivityCategoriesRxBinding>() {

    override fun getLayoutRes(): Int = R.layout.activity_categories_rx

    private val categoryViewModel: CategoryViewModelRx by viewModel()

    private val adapter: CategoryAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = categoryViewModel

        initAdapter()

        observeData()

        categoryViewModel.init()
    }

    private fun observeData() {
        categoryViewModel.presentation.observe(this, Observer {
            binding.presentation = it
        })

        categoryViewModel.categories.observe(this, Observer {
            adapter.notifyChanged(it)
        })

    }

    private fun initAdapter() {
        binding.rvCategories.verticalAdapter(adapter, R.drawable.bg_divider)
    }
}
