package com.aldev.moviecataloguemade.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movie_id")
    var movieId: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "release_date")
    var releaseDate: String,

    @ColumnInfo(name = "runtime")
    var runtime: Int,

    @ColumnInfo(name = "rating")
    var rating: Double,

    @ColumnInfo(name = "genre")
    var genres: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "poster_path")
    var posterPath: String,

    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String,

    @ColumnInfo(name = "type")
    var type: String,

    @ColumnInfo(name = "added_at")
    val addedAt: Long,
)