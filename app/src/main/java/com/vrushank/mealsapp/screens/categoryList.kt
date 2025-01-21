package com.vrushank.mealsapp.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.compose.AsyncImage
import com.vrushank.mealsapp.R
import com.vrushank.mealsapp.data.Catalog
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun CategoryListScreen(mealCategory:List<Catalog>, nav: (String) -> Unit) {



    Surface(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray)) {

        LazyColumn(verticalArrangement = Arrangement.Center
        , horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(10.dp, top = 30.dp, bottom = 30.dp)) {

            items(mealCategory){
                item->
                val encodedUrl = URLEncoder.encode(item.image,StandardCharsets.UTF_8.toString())
                Card(modifier = Modifier.padding(5.dp).clickable { nav("category_detail_screen/${item.id}/${item.name}/${encodedUrl}/${item.desc}") }.fillMaxWidth().height(200.dp)) {
                    Row() {
                        AsyncImage(
                            model = item.image,
                            contentDescription = item.name,
                            modifier = Modifier.padding(4.dp).fillParentMaxWidth(0.3f)
                                .align(alignment = Alignment.CenterVertically)
                            //contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(10.dp).height(200.dp))
                        Column(modifier = Modifier.padding(5.dp).fillParentMaxWidth(0.7f)) {
                            Text(item.name, fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding() )
                            Text(item.desc, fontSize = 16.sp, modifier = Modifier.fillMaxWidth().padding(end = 20.dp), overflow = TextOverflow.Ellipsis)
                        }
                    }
                }

            }
        }



    }
}
