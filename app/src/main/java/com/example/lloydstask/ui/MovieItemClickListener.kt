package com.example.lloydstask.ui

import com.example.lloydstask.domain.model.MovieDomainItem

interface MovieItemClickListener {
    fun onClick(movieDomainItem: MovieDomainItem)
}