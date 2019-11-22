package com.foodapp.presentation.meal

import android.os.Bundle
import androidx.lifecycle.Observer
import com.foodapp.R
import com.foodapp.databinding.ActivityMealBinding
import com.foodapp.presentation.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MealActivity : BaseActivity<ActivityMealBinding>() {

    companion object{
        const val EXTRA_MEAL = "meal"
    }

    private val mealViewModel: MealViewModel by viewModel()

    override fun getLayoutRes(): Int = R.layout.activity_meal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.mealViewModel = mealViewModel

        mealViewModel.init(intent.getParcelableExtra(EXTRA_MEAL))

        observeData()
    }

    private fun observeData(){
        mealViewModel.meal.observe(this, Observer {
            binding.meal = it
        })
    }

}