package com.pravin.dazngallery.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pravin.dazngallery.domain.ImageListUseCase
import com.pravin.dazngallery.domain.model.Gallery
import com.pravin.dazngallery.ui.home.ImageListState
import com.pravin.dazngallery.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val imageUserCase: ImageListUseCase
) : ViewModel() {

    private val _imageListState = MutableStateFlow(ImageListState())
    var imageListState: StateFlow<ImageListState> = _imageListState


    fun loadImages(name: String) = viewModelScope.launch(Dispatchers.IO) {
        imageUserCase(name = name).collect { it ->
            when (it) {
                is ResponseState.Error -> {
                    _imageListState.value =
                        ImageListState(error = it.exception.message ?: "Something went wrong")
                }

                is ResponseState.Loaded -> {
                    val sortedList = it.data.sortedWith(compareByDescending { img -> img.date })
                    _imageListState.value = ImageListState(imageList = sortedList)
                }

                is ResponseState.Loading -> {
                    _imageListState.value = ImageListState(isLoading = true)

                }
            }
        }
    }


    // another option is we want to sort a date by parsing the date format
    private val SORT_BY_DATE_FORMAT = compareByDescending<Gallery> {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        var convertedDate = Date()
        try {
            convertedDate = it.date.let { it1 -> format.parse(it1) } as Date
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        convertedDate
    }


}