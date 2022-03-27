package com.example.movies_app.presentation.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseApi (
    @SerializedName("results")
    @Expose
     val result : List< MovieModel>?
)