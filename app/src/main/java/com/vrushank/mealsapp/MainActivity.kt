package com.vrushank.mealsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.key
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory

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
            //var query:String? = ""
            val _query = MutableLiveData<String>("")
            val query: LiveData<String> = _query

         /*   val vm: CategoryListViewModel by viewModels {
                println("VD: passing query - main")
                ViewModelFactory("", MealCategoryRepository())
            }
            val mealCategoryUiState = vm.mealCategories
            val seachValue = vm.SeachMeal*/
//            val vm1: CategoryListViewModel by viewModels {
//                println("VD: passing query - vm1 main")
//                ViewModelFactory(query.toString(), MealCategoryRepository())
//            }


            MealsAppTheme {


                NavHost(navController, startDestination = "category_list/") {

//                    composable(
//                        route = "category_list"
//                    ) {
////                        val viewModel: CategoryListViewModel by viewModels {
////                            ViewModelFactory(query = "", MealCategoryRepository())
////                        }
//
//                        //val seachValue = vm.SeachMeal
//
//                        // Init Viewmodel Here
//                        CategoryListScreen(mealCategoryUiState.value, nav = {
//                            navController.navigate(it)
//                        }, mealSearch = {
//
//                            navController.navigate(it)
//                        }, seachValue.value, { navController.popBackStack() })
//                    }

                    composable(
                        route = "category_list/{query}",
                        arguments = listOf(navArgument("query") {
                            type = NavType.StringType
                            defaultValue = ""
                        })
                    ){ backStackEntry ->

                        val query =  backStackEntry.arguments?.getString("query")

                      /*  val vm: CategoryListViewModel by viewModels {
                            println("VD: passing query - main and")
                            ViewModelFactory(query, MealCategoryRepository())
                        }*/
                        val vm : CategoryListViewModel = viewModel(
                            key = query,
                            factory = ViewModelFactory(query, MealCategoryRepository())
                            )
                        println("instance of this viewmodel : $vm")

                        val mealCategoryUiState = vm.mealCategories
                        val seachValue = vm.SeachMeal

                       /* if (!query.value.isNullOrEmpty()) {
                            vm.searchMeal(query.value)
                        }*/
                        //val mealCategoryUiState = vm1.mealCategories
                       // val seachValue = vm.SeachMeal
                        //vm1.searchMeal(query.toString())

                        //print("call - $query")
                        //viewModel1.searchMeal(query)
                        CategoryListScreen(
                            mealCategoryUiState.value,
                            nav = { navController.navigate(it) },
                            mealSearch = { navController.navigate(it) },
                            seachValue.value,
                            {
                                navController.popBackStack()

                            })

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

