package com.evaluation.search.interaction

import androidx.annotation.MainThread
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.evaluation.adapter.viewholder.item.BaseItemView
import com.evaluation.search.datasource.AppDataSourceFactory
import com.evaluation.utils.Listing
import java.util.concurrent.Executor
import javax.inject.Inject

/**
 * @author Vladyslav Havrylenko
 * @since 09.10.2020
 */
class AppInteractionImpl @Inject constructor(
    private val factory: AppDataSourceFactory,
    private val config: PagedList.Config,
    private val networkExecutor: Executor
) : AppInteraction {

    @MainThread
    override fun repositoryList(query: String, initBackgroundState: Boolean): Listing<BaseItemView> {
        factory.initBackgroundState = initBackgroundState
        factory.query = query

        val liveList =
            LivePagedListBuilder(factory, config)
                .setFetchExecutor(networkExecutor)
                .build()

        return Listing(
            pagedList = liveList,
            networkState = factory.network,
            backgroundState = factory.background,
        )
    }
}
