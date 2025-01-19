package com.vrushank.mealsapp.repository

import com.vrushank.mealsapp.data.remote.MealCategory
import com.vrushank.mealsapp.data.remote.MealsApi
import com.vrushank.mealsapp.data.retrofit.RetrofitInstance
import com.vrushank.mealsapp.util.Resource

class MealCategoryRepository() {
    val api = RetrofitInstance.api
    suspend fun mealRepository():Resource<MealCategory>{
        val response = try {
            api.getMealCategories()
        }
        catch (e:Exception){
            return Resource.Error("Error has occured")
        }
        return Resource.Success(response)

    }
}