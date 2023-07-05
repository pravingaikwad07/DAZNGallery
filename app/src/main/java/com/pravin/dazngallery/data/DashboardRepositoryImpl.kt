package com.pravin.dazngallery.data

import android.content.Context
import com.pravin.dazngallery.domain.DashboardRepository
import com.pravin.dazngallery.domain.model.Gallery
import com.pravin.dazngallery.utils.getList
import com.pravin.dazngallery.utils.loadJSONFromAssets
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class DashboardRepositoryImpl @Inject constructor(@ApplicationContext val context: Context) :
    DashboardRepository {

    /**
     * This function will load the asset file and then converts it to the
     * gallery list Params: name - Name of the json asset file with extension
     */
    override suspend fun loadJson(name: String): List<Gallery> {
        val json = context.loadJSONFromAssets(name)
        return getList(json, Gallery::class.java) ?: emptyList()
    }
}

