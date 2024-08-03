package com.pixabayfreephotos.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pixabayfreephotos.ui.theme.PixabayFreePhotosTheme
import dagger.hilt.android.AndroidEntryPoint

const val phLog = "LogPhoto"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PixabayFreePhotosTheme {
                val modifier: Modifier = Modifier
                Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
                    PixabayApp(modifier = modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun PixabayApp(modifier: Modifier) {
    val photoViewModel: PhotoViewModel = viewModel()
    HomeScreen(
        photoUIState = photoViewModel.photoUIState,
        retryAction = photoViewModel::getPhoto,
        viewModel = photoViewModel,
        modifier = modifier
    )
}
