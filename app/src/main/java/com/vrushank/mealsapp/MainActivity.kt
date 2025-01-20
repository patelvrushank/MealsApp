package com.vrushank.mealsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vrushank.mealsapp.repository.MealCategoryRepository
import com.vrushank.mealsapp.screens.CategoryListScreen
import com.vrushank.mealsapp.screens.CategoryListViewModel
import com.vrushank.mealsapp.ui.theme.MealsAppTheme
import com.vrushank.mealsapp.util.ViewModelFactory

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: CategoryListViewModel by viewModels {
                ViewModelFactory(MealCategoryRepository())
            }
            MealsAppTheme {
                val mealCategoryUiState=viewModel.mealCategories
                CategoryListScreen(mealCategoryUiState.value)

                }
            }
        }
    }

