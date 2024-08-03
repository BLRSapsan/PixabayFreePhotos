package com.pixabayfreephotos.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pixabayfreephotos.data.toDomain
import com.pixabayfreephotos.domain.Repository
import com.pixabayfreephotos.domain.model.Hits
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface PhotoUIState {
    data object Loading : PhotoUIState
    data object Error : PhotoUIState
    data class PhotosListPage(val hits: List<Hits>) : PhotoUIState
}

@HiltViewModel
class PhotoViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    var photoUIState: PhotoUIState by mutableStateOf(PhotoUIState.Loading)
        private set

    private var photos by mutableStateOf(listOf<Hits>())
    private var currentPage = 1

    init {
        getPhoto()
    }

    fun getPhoto() {
        viewModelScope.launch {
            val result = repository.getPhoto(currentPage)
            result.fold(onSuccess = { data ->
                photos = photos + data.hits.map { it.toDomain() }
                photoUIState = PhotoUIState.PhotosListPage(photos)
                currentPage++
            }, onFailure = {
                photoUIState = PhotoUIState.Error
            })
        }
    }
}