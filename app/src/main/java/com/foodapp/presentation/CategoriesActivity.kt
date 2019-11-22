package com.foodapp.presentation

import android.os.Bundle
import androidx.lifecycle.Observer
import com.foodapp.R
import com.foodapp.databinding.ActivityCategoriesBinding
import com.foodapp.presentation.base.BaseActivity
import com.foodapp.presentation.category.CategoryAdapter
import com.foodapp.presentation.category.CategoryViewModel
import com.foodapp.presentation.ui.extensions.verticalAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoriesActivity : BaseActivity<ActivityCategoriesBinding>() {

    override fun getLayoutRes(): Int = R.layout.activity_categories

    private val categoryViewModel: CategoryViewModel by viewModel()

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
