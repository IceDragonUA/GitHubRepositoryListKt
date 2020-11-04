package com.evaluation.search.network

import com.evaluation.search.model.RepositoriesList
import io.reactivex.Single

interface AppRestApiDao {

    fun repositoryList(query: String, page: Int, perPage: Int): Single<RepositoriesList>

}