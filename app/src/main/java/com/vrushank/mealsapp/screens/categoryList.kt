package com.vrushank.mealsapp.screens


import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.vrushank.mealsapp.data.Catalog
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryListScreen(
    mealCategory: List<Catalog>,
    nav: (String) -> Unit,
    mealSearch: (String) -> Unit,
    seachMeal: List<Catalog>,
    onBack: () -> Unit
) {


    var displayList by remember { mutableStateOf(mealCategory) }

    var coroutineScope = rememberCoroutineScope()
    var queryText by remember { mutableStateOf("Search by letter") }
    var searchActive by remember { mutableStateOf(false) }
    var isVisible by remember { mutableStateOf(false) }
    var backButtonClick by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(true) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        val lazyListScroll = rememberLazyListState()
        val offset =
            remember { derivedStateOf { lazyListScroll.firstVisibleItemScrollOffset + lazyListScroll.firstVisibleItemIndex * 100 } }

        val textFieldHeight by animateDpAsState(
            targetValue = if (offset.value > 100) 0.dp else 60.dp,
            animationSpec = tween(durationMillis = 300)
        )

        val searchBarPadding by animateDpAsState(
            targetValue = if (offset.value > 100) 8.dp else 16.dp,
            animationSpec = tween(durationMillis = 300)
        )

        Column {
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, start = 0.dp, end = 10.dp)
                    .height(textFieldHeight)
            ) {
                Row {
                    // If Query is not null then show back arrow.
                    if (isVisible) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "back arrow",
                            modifier = Modifier

                                .padding(start = 0.dp, top = 10.dp, end = 5.dp)
                                .size(40.dp)
                                .clickable {
                                    backButtonClick = true
                                    onBack()
                                }
                        )
                    }

                    BasicTextField(
                        value = queryText,

                        onValueChange = { q -> queryText = q },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .padding(top = 15.dp, end = 10.dp)
                            .border(
                                border = BorderStroke(2.dp, Color.Black),
                                shape = RoundedCornerShape(5.dp)
                            )
                            .padding(5.dp)


                    )
                    Button(
                        onClick = {
                            if (queryText != "Search by letter") {
                                backButtonClick = false
                                mealSearch("category_list/$queryText")



                            }


                        },
                        modifier = Modifier
                            .padding(10.dp)
                            .align(alignment = Alignment.CenterVertically)
                    ) {
                        Text("Search")
                    }
                }
            }




            LazyColumn(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                state = lazyListScroll,
                modifier = Modifier.padding(10.dp, bottom = 30.dp)
            ) {
                if (seachMeal.isNotEmpty() && !backButtonClick) {
                    displayList = seachMeal
                    isVisible = true
                    println("Visible $seachMeal")
                } else {
                    displayList = mealCategory
                    isVisible = false

                }

                items(displayList) { item ->

                    val encodedUrl =
                        URLEncoder.encode(item.image, StandardCharsets.UTF_8.toString())
                    Card(modifier = Modifier
                        .padding(5.dp)
                        .clickable { nav("category_detail_screen/${item.id}/${item.name}/${encodedUrl}/${item.desc}") }
                        .fillMaxWidth()
                        .height(200.dp)) {
                        Row {
                            AsyncImage(
                                model = item.image,
                                contentDescription = item.name,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .fillParentMaxWidth(0.3f)
                                    .align(alignment = Alignment.CenterVertically)

                            )
                            Spacer(
                                modifier = Modifier
                                    .width(10.dp)
                                    .height(200.dp)
                            )
                            Column(
                                modifier = Modifier
                                    .padding(5.dp)
                                    .fillParentMaxWidth(0.7f)
                            ) {
                                Text(
                                    item.name,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding()
                                )
                                Text(
                                    item.desc, fontSize = 16.sp, modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(end = 20.dp), overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
        }


    }


}


