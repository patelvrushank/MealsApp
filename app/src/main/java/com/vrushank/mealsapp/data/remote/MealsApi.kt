package com.vrushank.mealsapp.data.remote

import retrofit2.http.GET

interface MealsApi {
    @GET("categories")
    suspend fun getMealCategories():MealCategory
}