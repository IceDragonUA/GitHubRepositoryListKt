package com.evaluation.search.model

import com.google.gson.annotations.SerializedName

/**
 * @author Vladyslav Havrylenko
 * @since 07.10.2020
 */
data class Repository(
    @SerializedName("name")
    val name: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("owner")
    val owner: User,
    @SerializedName("html_url")
    val html_url: String
)