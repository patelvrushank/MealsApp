package com.vrushank.mealsapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun CategoryDetailScreen(meal: MealType) {
    val scrollState = rememberScrollState()

    val scale = 1f - (scrollState.value / 600f).coerceIn(0f, 0.5f)
    val imageUrl = URLDecoder.decode(meal.image, StandardCharsets.UTF_8.toString())
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(10.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.verticalScroll(scrollState).padding(top = 30.dp, bottom = 30.dp)
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = meal.id,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(300.dp)
                    .fillMaxWidth()
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale
                    )

            )
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth().padding(10.dp)){
                meal.desc?.let { Text(text = it, fontSize = 20.sp, fontWeight = FontWeight.SemiBold) }

            }

        }
    }

}

data class MealType(val id: String?, val name: String?, val image: String?, val desc: String?)