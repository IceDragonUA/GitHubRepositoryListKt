package com.evaluation.favorites.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Vladyslav Havrylenko
 * @since 07.10.2020
 */
@Entity(tableName = "repositories")
data class RepositoryTableItem(
    @PrimaryKey
    val id: Int,
    val name: String,
    val avatar_url: String,
    val html_url: String
)