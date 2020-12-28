package com.github.jmlb23.mediumclone.data.models

import com.github.jmlb23.mediumclone.data.models.Profile

data class Article(
      var slug: String,
      var title: String,
      var description: String,
      var body: String,
      var tagList: List<String>,
      var createdAt: String,
      var updatedAt: String,
      var favorited: Boolean,
      var favoritesCount: Int,
      var author: Profile
)
