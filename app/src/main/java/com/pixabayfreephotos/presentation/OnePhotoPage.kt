package com.pixabayfreephotos.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.pixabayfreephotos.domain.model.Hits

@Composable
fun OnePhotoPage(photo: Hits, onDismiss: () -> Unit, modifier: Modifier) {

    val scrollState = rememberScrollState()
    Surface(modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(state = scrollState, true),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            AsyncImage(
                model = photo.largeImageURL,
                contentDescription = null,
                modifier = modifier.fillMaxSize()
            )
            Button(onClick = onDismiss) {
                Text(text = "Back")
            }
        }
    }
}