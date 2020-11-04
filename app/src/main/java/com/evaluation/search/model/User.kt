package com.evaluation.search.model

import com.google.gson.annotations.SerializedName

/**
 * @author Vladyslav Havrylenko
 * @since 04.11.2020
 */
data class User(
    @SerializedName("avatar_url")
    val avatar_url: String
)