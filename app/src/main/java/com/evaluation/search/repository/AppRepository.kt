package com.evaluation.search.repository

import com.evaluation.adapter.viewholder.item.BaseItemView
import io.reactivex.disposables.Disposable

/**
 * @author Vladyslav Havrylenko
 * @since 04.11.2020
 */
interface AppRepository {

    fun repositoryListInit(
        query: String,
        page: Int,
        perPage: Int,
        onPrepared: () -> Unit,
        onSuccess: (MutableList<BaseItemView>) -> Unit,
        onError: (MutableList<BaseItemView>) -> Unit
    ): Disposable

    fun repositoryListPaged(
        query: String,
        page: Int,
        perPage: Int,
        onPrepared: () -> Unit,
        onSuccess: (MutableList<BaseItemView>) -> Unit,
        onError: () -> Unit
    ): Disposable
}