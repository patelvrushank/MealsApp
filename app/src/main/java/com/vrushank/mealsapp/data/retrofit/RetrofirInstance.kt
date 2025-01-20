package com.vrushank.mealsapp.data.retrofit

import com.vrushank.mealsapp.constants.URL
import com.vrushank.mealsapp.data.remote.MealsApi
import okhttp3.OkHttpClient

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofirInstance {

 val retrofit:Retrofit by lazy {

     Retrofit.Builder()
         .baseUrl(URL)
         .addConverterFactory(GsonConverterFactory.create())
         .client(
             OkHttpClient.Builder()
                 .addInterceptor(HeaderIntercepter())
                 .build()
         )
         .build()
 }
     val api : MealsApi by lazy{
         retrofit.create(MealsApi::class.java)
     }
 }


