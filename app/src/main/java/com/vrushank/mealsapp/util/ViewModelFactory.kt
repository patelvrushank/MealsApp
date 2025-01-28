package com.vrushank.mealsapp.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vrushank.mealsapp.repository.MealCategoryRepository
import com.vrushank.mealsapp.screens.CategoryListViewModel

class ViewModelFactory(private val query:String ? = "",private val repository: MealCategoryRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CategoryListViewModel::class.java)){
            @Suppress("UNCKECKED_CAST")
            return query?.let { CategoryListViewModel(it,repository) } as T


        }
        throw IllegalArgumentException("Unknown viewModel class")
    }
}