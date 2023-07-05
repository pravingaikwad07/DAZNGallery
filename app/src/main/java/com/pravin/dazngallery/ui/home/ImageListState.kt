package com.pravin.dazngallery.ui.home

import com.pravin.dazngallery.domain.model.Gallery

data class ImageListState(
    val isLoading: Boolean = false,
    val imageList: List<Gallery> = emptyList(),
    val error: String = ""
)
