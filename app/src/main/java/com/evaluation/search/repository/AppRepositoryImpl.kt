package com.evaluation.search.repository

import android.content.Context
import com.evaluation.R
import com.evaluation.adapter.viewholder.item.BaseItemView
import com.evaluation.adapter.viewholder.item.CardItemView
import com.evaluation.adapter.viewholder.item.NoItemView
import com.evaluation.search.network.AppRestApiDao
import com.evaluation.utils.defIfNull
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject


/**
 * @author Vladyslav Havrylenko
 * @since 01.05.2020
 */
class AppRepositoryImpl @Inject constructor(
    private val context: Context,
    private val appRestApiDao: AppRestApiDao
) : AppRepository {

    override fun repositoryListInit(
        query: String,
        page: Int,
        perPage: Int,
        onPrepared: () -> Unit,
        onSuccess: (MutableList<BaseItemView>) -> Unit,
        onError: (MutableList<BaseItemView>) -> Unit
    ): Disposable {
        return appRestApiDao.repositoryList(query = query, page = page, perPage = perPage)
            .doOnSubscribe {
                onPrepared()
            }
            .subscribe(
                { list ->
                    val itemList: MutableList<BaseItemView> = mutableListOf()
                    list.items.forEach {
                        itemList.add(CardItemView(id = it.id.defIfNull().toString(), repository = it))
                    }
                    itemList.ifEmpty {
                        itemList.add(
                            NoItemView(
                                title = context.resources.getString(R.string.result).defIfNull()
                            )
                        )
                    }
                    onSuccess(itemList)
                },
                { errorMessage ->
                    Timber.e(errorMessage, "Loading error")
                    val itemList: MutableList<BaseItemView> = mutableListOf()
                    itemList.add(
                        NoItemView(
                            title = context.resources.getString(R.string.result).defIfNull()
                        )
                    )
                    onError(itemList)
                })
    }

    override fun repositoryListPaged(
        query: String,
        page: Int,
        perPage: Int,
        onPrepared: () -> Unit,
        onSuccess: (MutableList<BaseItemView>) -> Unit,
        onError: () -> Unit
    ): Disposable {
        return appRestApiDao.repositoryList(query = query, page = page, perPage = perPage)
            .doOnSubscribe {
                onPrepared()
            }
            .subscribe(
                { list ->
                    val itemList: MutableList<BaseItemView> = mutableListOf()
                    list.items.forEach {
                        itemList.add(CardItemView(id = it.id.defIfNull().toString(), repository = it))
                    }
                    onSuccess(itemList)
                },
                { errorMessage ->
                    Timber.e(errorMessage, "Loading error")
                    onError()
                }
            )
    }
}
