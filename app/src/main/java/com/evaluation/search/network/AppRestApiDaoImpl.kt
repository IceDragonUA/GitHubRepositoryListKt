package com.evaluation.search.network

import com.evaluation.executor.ThreadExecutor
import com.evaluation.network.RestApi
import com.evaluation.search.model.RepositoriesList
import io.reactivex.Single
import javax.inject.Inject

class AppRestApiDaoImpl @Inject constructor(
    private val appRest: RestApi,
    private val executor: ThreadExecutor
): AppRestApiDao {

    override fun repositoryList(query: String, page: Int, perPage: Int): Single<RepositoriesList> {
        return appRest.repositoryList(query, page, perPage)
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }

}