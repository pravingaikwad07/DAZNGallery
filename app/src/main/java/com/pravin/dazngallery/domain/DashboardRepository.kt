package com.pravin.dazngallery.domain

import com.pravin.dazngallery.domain.model.Gallery

interface DashboardRepository {
    suspend fun loadJson(name: String): List<Gallery>
}