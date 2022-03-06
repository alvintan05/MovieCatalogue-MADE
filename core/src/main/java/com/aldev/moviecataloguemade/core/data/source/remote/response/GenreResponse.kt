package com.aldev.moviecataloguemade.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @field:SerializedName("id")
    var id: Int?,

    @field:SerializedName("name")
    var name: String?
)