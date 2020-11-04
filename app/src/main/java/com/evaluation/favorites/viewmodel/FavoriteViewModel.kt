package com.evaluation.favorites.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.ViewModel
import com.evaluation.adapter.viewholder.item.BaseItemView
import com.evaluation.adapter.viewholder.item.CardItemView
import com.evaluation.favorites.database.AppDatabaseDao
import com.evaluation.mapper.Mapper
import com.evaluation.search.model.Repository
import com.evaluation.utils.defIfNull
import com.evaluation.utils.toLiveData

/**
 * @author Vladyslav Havrylenko
 * @since 07.10.2020
 */
class FavoriteViewModel @ViewModelInject constructor(
    private val database: AppDatabaseDao,
    private val mapper: Mapper
) : ViewModel() {

    val items = database.favoriteList().map {
        val itemList: MutableList<BaseItemView> = mutableListOf()
        it.forEach { item ->
            itemList.add(CardItemView(id = item.id.defIfNull().toString(), repository = mapper.toViewItem(item)))
        }
        itemList
    }.toLiveData()

    val backgroundState = map(items) { it.isEmpty() }

    fun deleteFavorite(item: Repository) {
        database.deleteItem(item.id.defIfNull().toString())
    }

}