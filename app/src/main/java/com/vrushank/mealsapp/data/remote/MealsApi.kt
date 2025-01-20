package com.vrushank.mealsapp.data.remote

import retrofit2.http.GET

interface MealsApi {
    @GET("categories.php")
    suspend fun getMealCategories():MealCategory
}