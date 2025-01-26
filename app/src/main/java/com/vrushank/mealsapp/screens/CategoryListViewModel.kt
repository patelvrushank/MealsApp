package com.vrushank.mealsapp.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vrushank.mealsapp.data.Catalog
import com.vrushank.mealsapp.data.remote.Category
import com.vrushank.mealsapp.data.remote.MealCategory
import com.vrushank.mealsapp.data.retrofit.SeachMeal
import com.vrushank.mealsapp.repository.MealCategoryRepository
import com.vrushank.mealsapp.util.Resource
import kotlinx.coroutines.launch

class CategoryListViewModel(
    //private val query: String = "",
    private val repository: MealCategoryRepository = MealCategoryRepository()
) : ViewModel() {

    private val _mealCategories = mutableStateOf<List<Catalog>>(listOf())
    val mealCategories: MutableState<List<Catalog>> get() = _mealCategories
    private val _SeachMeal = mutableStateOf<List<Catalog>>(listOf())
    val SeachMeal: MutableState<List<Catalog>> get() = _SeachMeal

    init {
       // if(query.isEmpty()) {
            loadMealList()
//        }else{
//
//        }
    }

    fun loadMealList() {
        viewModelScope.launch {
            val result = repository.mealRepository()
            // val instruction = repository.getInsruction("a")
            //println("VD-Instruction- ${instruction.data?.meals?.toString()}")
            when (result) {
                is Resource.Success -> {
                    val categories =
                        result.data?.categories?.mapIndexed { index, category ->
                            Catalog(
                                category.idCategory,
                                category.strCategory,
                                category.strCategoryThumb,
                                category.strCategoryDescription
                            )
                        } as List<Catalog>

                    _mealCategories.value = categories

                }

                is Resource.Error -> {
                    println("Error occued in the data")
                }

                is Resource.Loading -> {

                }


            }

        }
    }

    fun searchMeal(key: String) {
        viewModelScope.launch {
            val searchResult = repository.getInsruction(key)
            when (searchResult) {
                is Resource.Success -> {
                    val meals =
                        searchResult.data?.meals?.mapIndexed { index, meal ->
                            Catalog(
                                meal.idMeal,
                                name = meal.strMeal,
                                meal.strMealThumb,
                                meal.strIngredient1
                            )
                            // SeachMeal(meal.strCategory)
                        } as List<Catalog>
                    _SeachMeal.value = meals
                }

                is Resource.Error -> {
                    println("Could  not find the meal which you are seaching for")
                }

                is Resource.Loading -> {

                }


            }

        }
    }
}