package com.vrushank.mealsapp.data.remote

import com.vrushank.mealsapp.data.remote.Instruction.Instruction
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApi {
    @GET("categories.php")
    suspend fun getMealCategories():MealCategory

    @GET("search.php")
    suspend fun getInstruction(
        @Query("f")
        item:String? = ""
    ):Instruction

}