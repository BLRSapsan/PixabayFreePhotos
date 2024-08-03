package com.pixabayfreephotos.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pixabayfreephotos.domain.model.Hits

@Composable
fun PhotosListPage(
    listPhoto: List<Hits>,
    modifier: Modifier,
    viewModel: PhotoViewModel
) {
    val openPhotoIndex = rememberSaveable { mutableStateOf<Int?>(null) }
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        content = {
            itemsIndexed(listPhoto) { index, item ->
                PhotoItem(item, Modifier.clickable {
                    openPhotoIndex.value = index })

                if (index >= listPhoto.size - 1) {
                    viewModel.getPhoto()
                }
            }
        },
        modifier = modifier.fillMaxSize()
    )

    if (openPhotoIndex.value != null) {
        OnePhotoPage(
            photo = listPhoto[openPhotoIndex.value!!],
            onDismiss = { openPhotoIndex.value = null },
            modifier = modifier
        )
    }
}


@Composable
fun PhotoItem(item: Hits, modifier: Modifier) {
    AsyncImage(
        model = item.webformatURL,
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )
}