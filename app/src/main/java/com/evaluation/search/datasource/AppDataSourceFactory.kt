package com.evaluation.search.datasource

import androidx.paging.DataSource
import com.evaluation.adapter.viewholder.item.BaseItemView
import com.evaluation.utils.empty
import javax.inject.Inject

/**
 * @author Vladyslav Havrylenko
 * @since 08.10.2020
 */

class AppDataSourceFactory @Inject constructor(private var dataSource: AppDataSource) :
    DataSource.Factory<Int, BaseItemView>() {

    var initBackgroundState = true
    var query = empty()
    var network = dataSource.network
    var background = dataSource.background

    override fun create(): DataSource<Int, BaseItemView> {
        dataSource.initBackgroundState = initBackgroundState
        dataSource.query = query
        return dataSource
    }

}