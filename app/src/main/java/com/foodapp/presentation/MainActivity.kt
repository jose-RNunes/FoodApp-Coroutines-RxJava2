package com.foodapp.presentation

import android.content.Intent
import android.os.Bundle
import com.foodapp.R
import com.foodapp.databinding.ActivityMainBinding
import com.foodapp.presentation.base.BaseActivity
import com.foodapp.presentation.category.coroutines.CategoriesActivity
import com.foodapp.presentation.category.rx.CategoriesActivityRx

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.coroutines.setOnClickListener {
            startActivity(Intent(this, CategoriesActivity::class.java))
        }

        binding.rx.setOnClickListener {
            startActivity(Intent(this,CategoriesActivityRx::class.java))
        }
    }
}