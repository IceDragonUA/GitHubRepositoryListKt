package com.evaluation.search.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import com.evaluation.favorites.database.AppDatabaseDao
import com.evaluation.mapper.Mapper
import com.evaluation.search.interaction.AppInteraction
import com.evaluation.search.model.Repository
import com.evaluation.utils.defIfNull
import com.evaluation.utils.empty

/**
 * @author Vladyslav Havrylenko
 * @since 07.10.2020
 */
class RepositoryViewModel @ViewModelInject constructor(
    private val interaction: AppInteraction,
    private val database: AppDatabaseDao,
    private val mapper: Mapper
) : ViewModel() {

    private var initBackgroundState = true
    private val query = MutableLiveData<String>()
    private val itemResult = map(query) {
        interaction.repositoryList(it, initBackgroundState)
    }
    val items = switchMap(itemResult) { it.pagedList }
    val networkState = switchMap(itemResult) { it.networkState }
    val backgroundState = switchMap(itemResult) { it.backgroundState }

    init {
        this.query.value = empty()
    }

    fun search(query: String, initBackgroundState: Boolean) {
        this.initBackgroundState = initBackgroundState
        this.query.value = query
    }

    fun addFavorite(item: Repository) {
        database.insertItem(mapper.toTableItem(item))
    }

}