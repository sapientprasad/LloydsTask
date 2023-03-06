package com.example.lloydstask.domain.model

data class MovieListDomainModel(
    val movieDomainItemList: List<MovieDomainItem>,
    val errorMessage: String?
)

data class MovieDomainItem(
    val id: String,
    val title: String,
    val imageUrl: String
)
