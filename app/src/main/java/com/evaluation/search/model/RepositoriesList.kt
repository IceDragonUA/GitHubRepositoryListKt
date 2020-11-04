package com.evaluation.search.model

import com.google.gson.annotations.SerializedName

/**
 * @author Vladyslav Havrylenko
 * @since 07.10.2020
 */
data class RepositoriesList(
    @SerializedName("items")
    val items: List<Repository>
)