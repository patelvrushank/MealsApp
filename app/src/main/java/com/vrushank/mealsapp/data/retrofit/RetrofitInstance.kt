package com.vrushank.mealsapp.data.retrofit

import com.vrushank.mealsapp.constants.URL
import com.vrushank.mealsapp.data.remote.MealsApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        private val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val api: MealsApi by lazy {
            retrofit.create(MealsApi::class.java)
        }
    }
}