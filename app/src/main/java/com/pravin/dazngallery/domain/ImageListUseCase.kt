package com.pravin.dazngallery.domain

import com.pravin.dazngallery.domain.model.Gallery
import com.pravin.dazngallery.utils.ResponseState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ImageListUseCase @Inject constructor(
    private val repository: DashboardRepository
) {
    operator fun invoke(name: String): Flow<ResponseState<List<Gallery>>> = flow {
        try {
            emit(ResponseState.Loading())
            val images = repository.loadJson(name)
            // delay to showcase the Loading using states. (mocking delay as if we are getting response from server/api call
            delay(2000)
            emit(ResponseState.Loaded(images))

        } catch (e: Exception) {
            emit(ResponseState.Error(e))
        }
    }
}