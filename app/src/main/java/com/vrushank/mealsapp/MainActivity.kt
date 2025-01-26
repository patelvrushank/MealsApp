package com.vrushank.mealsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels

import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vrushank.mealsapp.repository.MealCategoryRepository
import com.vrushank.mealsapp.screens.CategoryDetailScreen
import com.vrushank.mealsapp.screens.CategoryListScreen
import com.vrushank.mealsapp.screens.CategoryListViewModel
import com.vrushank.mealsapp.screens.MealType
import com.vrushank.mealsapp.ui.theme.MealsAppTheme
import com.vrushank.mealsapp.util.ViewModelFactory

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val viewModel: CategoryListViewModel by viewModels {
                ViewModelFactory(MealCategoryRepository())
            }
            MealsAppTheme {
                val mealCategoryUiState = viewModel.mealCategories
                val seachValue = viewModel.SeachMeal

               // NavHost(navController, startDestination = "category_list/{query}") {
                NavHost(navController, startDestination = "category_list") {

                    composable(route = "category_list") {
                        // Init Viewmodel Here
                        CategoryListScreen(mealCategoryUiState.value, nav = {
                            navController.navigate(it)
                        }, mealSearch = {
                               // query -> navController.navigate("category_list")
                            viewModel.searchMeal(it)
                        }, seachValue.value)
                    }
                    composable(route = "category_detail_screen/{id}/{name}/{image}/{desc}",
                        arguments = listOf(
                            navArgument("id") {
                                type = NavType.StringType
                            }, navArgument("name") {
                                type = NavType.StringType
                            }, navArgument("image") {
                                type = NavType.StringType
                            }, navArgument("desc") {
                                type = NavType.StringType
                            }
                        )) { backStackEntry ->
                        val id = backStackEntry.arguments?.getString("id")
                        val name = backStackEntry.arguments?.getString("name")
                        val image = backStackEntry.arguments?.getString("image")
                        val desc = backStackEntry.arguments?.getString("desc")

                        CategoryDetailScreen(MealType(id, name, image, desc))

                    }
                }


            }
        }
    }
}

