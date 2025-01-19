package com.vrushank.mealsapp.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vrushank.mealsapp.data.Catalog
import com.vrushank.mealsapp.data.remote.Category
import com.vrushank.mealsapp.data.remote.MealCategory
import com.vrushank.mealsapp.repository.MealCategoryRepository
import com.vrushank.mealsapp.util.Resource
import kotlinx.coroutines.launch

class CategoryListViewModel(val repository: MealCategoryRepository = MealCategoryRepository()):ViewModel() {

    private val _mealCategories = mutableStateOf<List<Catalog>>(listOf())
    val mealCategories : MutableState<List<Catalog>> get() = _mealCategories

    init {
        loadMealList()
    }

    fun loadMealList()
    {
        viewModelScope.launch {
            val result = repository.mealRepository()
            when(result){
                is Resource.Success -> {
                    val categories =
                    result.data?.categories?.mapIndexed(){
                        index , category -> Catalog(category.strCategory)
                    } as List<Catalog>
                    _mealCategories.value += categories
                    println("VD ${_mealCategories.value}")
                }
                is Resource.Error -> {
                    println("Error occued in the data" )
                }
                is Resource.Loading -> {

                }
            }
        }
    }
}