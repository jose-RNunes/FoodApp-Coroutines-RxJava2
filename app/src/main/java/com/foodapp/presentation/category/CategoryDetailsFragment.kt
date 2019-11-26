package com.foodapp.presentation.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.foodapp.presentation.category.coroutines.CategoryViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CategoryDetailsFragment : Fragment(){

    private val categoryViewModel: CategoryViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun observeData(){

    }
}