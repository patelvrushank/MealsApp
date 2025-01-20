package com.vrushank.mealsapp.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrushank.mealsapp.data.Catalog



@Composable
fun CategoryListScreen(mealCategory:List<Catalog>) {



    Surface(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray)) {

        LazyColumn(verticalArrangement = Arrangement.Center
        , horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(10.dp)) {

            items(mealCategory){
                item ->  Text(item.toString(), fontSize = 16.sp)

            }
        }



    }
}