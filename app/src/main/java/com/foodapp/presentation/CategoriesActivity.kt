package com.foodapp.presentation

import android.os.Bundle
import android.util.Log
import com.foodapp.R
import com.foodapp.databinding.ActivityCategoriesBinding
import com.foodapp.presentation.base.BaseActivity
import com.foodapp.presentation.category.CategoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoriesActivity : BaseActivity<ActivityCategoriesBinding>() {

    override fun getLayoutRes(): Int = R.layout.activity_categories

    private val categoryViewModel: CategoryViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = categoryViewModel

        observeData()

        categoryViewModel.init()
    }

    private fun observeData(){
        categoryViewModel.presentation.observeForever {
            binding.presentation = it
        }

        categoryViewModel.categories.observeForever {
            Log.i("TAG", "Categories $it")
        }

    }
}
