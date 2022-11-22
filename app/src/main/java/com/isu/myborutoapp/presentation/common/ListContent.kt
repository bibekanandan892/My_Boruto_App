package com.isu.myborutoapp.presentation.common

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.isu.myborutoapp.R
import com.isu.myborutoapp.domain.model.Hero
import com.isu.myborutoapp.navigation.Screen
import com.isu.myborutoapp.presentation.componenets.RatingWidget
import com.isu.myborutoapp.presentation.componenets.ShimmerEffect
import com.isu.myborutoapp.ui.theme.*
import com.isu.myborutoapp.utils.Constants.BASE_URL

@Composable
fun ListContent(
    heroes: LazyPagingItems<Hero>,
    navController: NavHostController,
) {val result = handlePagingResult(heroes = heroes)

    if (result) {
        LazyColumn(
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
        ) {
            items(
                items = heroes,
                key = { hero ->
                    hero.id
                }
            ) { hero ->
                hero?.let {
                    HeroItem(hero = it, navController = navController)
                }
            }
        }
    }


}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun HeroItem(
    hero: Hero,
    navController: NavHostController,
) {
    val painter = rememberImagePainter(data = "$BASE_URL${hero.image}") {
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }
    Box(modifier = Modifier
        .height(HERO_ITEM_HEIGHT)
        .clickable {
            navController.navigate(Screen.Details.passHeroId(heroId = hero.id))

        }, contentAlignment = Alignment.BottomStart) {
        Surface(modifier = Modifier.fillMaxSize(), shape = RoundedCornerShape(LARGE_PADDING)) {
            Image(painter = painter, contentDescription = stringResource(R.string.Hero_Image),
                contentScale = ContentScale.Crop)

        }
        Surface(modifier = Modifier
            .fillMaxHeight(0.4f)
            .fillMaxWidth(),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(
                bottomStart = LARGE_PADDING,
                bottomEnd = LARGE_PADDING)) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(all = MEDIUM_PADDING)) {
                Text(text = hero.name,
                    color = MaterialTheme.colors.topAppBarContentColor,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)
                Text(text = hero.about,
                    color = Color.White.copy(alpha = ContentAlpha.medium),
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis)
                Row(modifier = Modifier.padding(top = SMALL_PADDING),
                    verticalAlignment = Alignment.CenterVertically) {
                    RatingWidget(modifier = Modifier.padding(end = SMALL_PADDING),
                        rating = hero.rating)
                    Text(text = "(${hero.rating})",
                        textAlign = TextAlign.Center,

                        color = Color.White.copy(
                            alpha = ContentAlpha.medium))
                }
            }

        }

    }
}
@Composable
fun handlePagingResult(
    heroes: LazyPagingItems<Hero>
): Boolean {
    heroes.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }
            error != null -> {
                EmptyScreen(error = error, heroes = heroes)
                false
            }
            heroes.itemCount < 1 -> {
                EmptyScreen()
                false
            }
            else -> true
        }
    }
}
