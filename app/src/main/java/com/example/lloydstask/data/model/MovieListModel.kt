package com.example.lloydstask.data.model

data class MovieListModel(
    val movieItemList: List<MovieItem>,
    val errorMessage: String?
)

data class MovieItem(
    val id: String,
    val title: String,
    val imageUrl: String
)
