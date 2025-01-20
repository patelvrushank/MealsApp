package com.vrushank.mealsapp.repository

import com.vrushank.mealsapp.data.remote.MealCategory
import com.vrushank.mealsapp.data.retrofit.RetrofirInstance

import com.vrushank.mealsapp.util.Resource

class MealCategoryRepository() {
    val api = RetrofirInstance.api
    suspend fun mealRepository():Resource<MealCategory>{
        val response = try {
            api.getMealCategories()
        }
        catch (e:Exception){
            return Resource.Error("Error has occured")
        }
        println("VD:${response.categories.toString()}")
        return Resource.Success(response)

    }
}