package com.retrofitwithcoroutines.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class User(
    @SerializedName("id")
    val id:Int,
    @SerializedName("albumId")
    val albumId:Int,
    @SerializedName("title")
    val title:String,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl:String,
    @SerializedName("url")
    val url:String
):Serializable
