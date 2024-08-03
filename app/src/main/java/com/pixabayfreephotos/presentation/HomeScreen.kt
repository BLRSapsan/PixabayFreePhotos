package com.pixabayfreephotos.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    photoUIState: PhotoUIState,
    retryAction: () -> Unit,
    modifier: Modifier,
    viewModel: PhotoViewModel
) {
    when (photoUIState) {
        is PhotoUIState.Loading -> LoadingPage(modifier = modifier)
        is PhotoUIState.Error -> ErrorPage(retryAction, modifier = modifier)
        is PhotoUIState.PhotosListPage -> PhotosListPage(
            listPhoto = photoUIState.hits,
            viewModel = viewModel,
            modifier = modifier
        )
    }
}