package com.pravin.dazngallery.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.pravin.dazngallery.CoroutinesTestRule
import com.pravin.dazngallery.domain.DashboardRepository
import com.pravin.dazngallery.domain.ImageListUseCase
import com.pravin.dazngallery.domain.model.Gallery
import com.pravin.dazngallery.ui.home.ImageListState
import com.pravin.dazngallery.utils.NASA_DETAILS_JSON
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


class DashboardViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var repository: DashboardRepository
    private lateinit var viewModel: DashboardViewModel
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var imageListUseCase: ImageListUseCase

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)

        imageListUseCase = ImageListUseCase(repository)
        viewModel = DashboardViewModel(imageListUseCase)
    }

    @Test
    fun `check if ImageState is working`() {
        runTest {
            doReturn(flowOf(emptyList<Gallery>())).`when`(repository).loadJson(NASA_DETAILS_JSON)
            viewModel.imageListState.test {
                assertEquals(ImageListState(), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }
    }


    @Test
    fun `check if ImageState is data`() {
        runTest {
            `when`(repository.loadJson(NASA_DETAILS_JSON)).thenReturn(getDummyList())
            viewModel.loadImages(NASA_DETAILS_JSON)
            testDispatcher.scheduler.advanceUntilIdle()
            Thread.sleep(4000)
            val data = viewModel.imageListState.value.imageList
            assertEquals(26, data.size)
        }
    }


    @After
    @Throws(java.lang.Exception::class)
    fun tearDown() {
        Dispatchers.resetMain()
    }


    private fun getDummyList(): List<Gallery> {
        val list = ArrayList<Gallery>()
        repeat(26) {
            list.add(
                Gallery(
                    copyright = "",
                    date = "",
                    explanation = "",
                    hdurl = "",
                    media_type = "",
                    service_version = "",
                    title = "",
                    url = ""
                )
            )
        }

        return list
    }


}