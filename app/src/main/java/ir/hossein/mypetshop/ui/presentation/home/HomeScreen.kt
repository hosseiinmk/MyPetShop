package ir.hossein.mypetshop.ui.presentation.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import ir.hossein.mypetshop.R
import ir.hossein.mypetshop.domain.model.Product
import ir.hossein.mypetshop.ui.theme.Green
import ir.hossein.mypetshop.ui.theme.White
import ir.hossein.mypetshop.ui.utils.carouselTransition

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsState()

    val bannerState: PagerState = rememberPagerState(pageCount = { state.products.size })

    val categoryIcons = remember { listOf(R.drawable.dog, R.drawable.cat, R.drawable.fish) }

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = bannerState,
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp),
            pageSpacing = 16.dp
        ) { page ->
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.carouselTransition(page, bannerState)
            ) {
                BannerImages(state.products[page].imagePath)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(bannerState.pageCount) {
                Box(
                    modifier = Modifier
                        .width(10.dp)
                        .height(10.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(
                            if (bannerState.currentPage == it) MaterialTheme.colorScheme.primary
                            else Color.LightGray
                        )
                )
                Spacer(modifier = Modifier.width(5.dp))
            }
        }
        Text(
            text = "دسته بندی ها",
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            repeat(3) {
                Card(
                    shape = RoundedCornerShape(50),
                    onClick = { viewModel.selectCategory(it) },
                    border = BorderStroke(
                        width = 1.dp,
                        color = if (state.selectedCategory == it) Green else White
                    )
                ) {
                    Image(
                        painter = painterResource(id = categoryIcons[it]),
                        contentDescription = null,
                        modifier = Modifier
                            .width(80.dp)
                            .height(80.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
        LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
            items(state.filteredProduct) {
                ProductItem(product = it)
            }
        })
    }
}

@Composable
fun BannerImages(imagePath: String) {
    AsyncImage(
        model = if (imagePath != "") imagePath else painterResource(id = R.drawable.banner),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ProductItem(product: Product) {
    Card(
        shape = RoundedCornerShape(10),
        modifier = Modifier.padding(8.dp)
    ) {
        AsyncImage(
            model = product.imagePath,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = product.name, Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "قیمت : ${product.price} تومان ",
            Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "تعداد : ${product.amount}",
            Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}