package com.pravin.dazngallery.ui.home


interface ListItemClickListener<T> {
    fun onItemClick(position: Int, item: T)
}