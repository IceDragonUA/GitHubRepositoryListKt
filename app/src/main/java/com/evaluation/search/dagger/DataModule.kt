package com.evaluation.search.dagger

import android.content.Context
import androidx.paging.PagedList
import com.evaluation.database.AppDatabase
import com.evaluation.executor.BaseExecutor
import com.evaluation.network.RestApi
import com.evaluation.favorites.database.AppDatabaseDao
import com.evaluation.search.datasource.AppDataSource
import com.evaluation.search.datasource.AppDataSourceFactory
import com.evaluation.search.interaction.AppInteraction
import com.evaluation.search.interaction.AppInteractionImpl
import com.evaluation.search.network.AppRestApiDao
import com.evaluation.search.network.AppRestApiDaoImpl
import com.evaluation.search.repository.AppRepository
import com.evaluation.search.repository.AppRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import java.util.concurrent.Executor
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun appDao(appDatabase: AppDatabase): AppDatabaseDao = appDatabase.appDao()

    @Singleton
    @Provides
    fun appRest(appRest: RestApi, executor: BaseExecutor): AppRestApiDao = AppRestApiDaoImpl(appRest, executor)

    @Singleton
    @Provides
    fun appRepository(context: Context, remoteDao: AppRestApiDao) : AppRepository =
        AppRepositoryImpl(context, remoteDao)

    @Singleton
    @Provides
    fun appDataSource(appRepository: AppRepository) = AppDataSource(appRepository)

    @Singleton
    @Provides
    fun appDataSourceFactory(appDataSource: AppDataSource) = AppDataSourceFactory(appDataSource)

    @Singleton
    @Provides
    fun appInteraction(factory: AppDataSourceFactory, config: PagedList.Config, networkExecutor: Executor): AppInteraction =
        AppInteractionImpl(factory, config, networkExecutor)

}