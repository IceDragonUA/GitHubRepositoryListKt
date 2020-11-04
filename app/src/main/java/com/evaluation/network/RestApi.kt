package com.evaluation.network

import com.evaluation.search.model.RepositoriesList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("search/repositories")
    fun repositoryList(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Single<RepositoriesList>

}