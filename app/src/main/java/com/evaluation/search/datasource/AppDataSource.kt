package com.evaluation.search.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.evaluation.adapter.viewholder.item.BaseItemView
import com.evaluation.adapter.viewholder.item.NoItemView
import com.evaluation.search.repository.AppRepository
import com.evaluation.search.repository.AppRepositoryImpl
import com.evaluation.utils.*
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * @author Vladyslav Havrylenko
 * @since 08.10.2020
 */
class AppDataSource @Inject constructor(
    private val repository: AppRepository
) : PageKeyedDataSource<Int, BaseItemView>() {

    var initBackgroundState = true
    var query = empty()
    val network = MutableLiveData<Boolean>()
    val background = MutableLiveData<Boolean>()

    private var disposeInit: Disposable? = null
    private var disposePaged: Disposable? = null

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, BaseItemView>) {
        disposeInit?.dispose()
        disposePaged?.dispose()
        disposeInit = repository.repositoryListInit(
            query = query,
            page = PAGE_START,
            perPage = PAGE_LIMIT,
            onPrepared = {
                postInitialState(initNetworkState(), initBackgroundState())
            },
            onSuccess = { list ->
                val refresh = list.firstOrNull() is NoItemView
                postInitialState(refreshNetworkState(refresh), refreshBackgroundState(refresh))
                callback.onResult(initList(list), null, initPaging(refresh))
            },
            onError = { list ->
                postInitialState(Listing.NetworkState.LOADED, Listing.BackgroundState.SHOW)
                callback.onResult(initList(list), null, null)
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, BaseItemView>) {
        disposeInit?.dispose()
        disposePaged?.dispose()
        disposePaged = repository.repositoryListPaged(
            query = query,
            page = params.key,
            perPage = params.requestedLoadSize,
            onPrepared = {
                postAfterState(Listing.NetworkState.LOADING)
            },
            onSuccess = { list ->
                postAfterState(Listing.NetworkState.LOADED)
                callback.onResult(list, params.key + 1)
            },
            onError = {
                postAfterState(Listing.NetworkState.LOADED)
                callback.onResult(listOf(), null)
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, BaseItemView>) {

    }

    private fun postInitialState(networkState: Listing.NetworkState, backgroundState: Listing.BackgroundState) {
        network.postValue(networkState.value())
        background.postValue(backgroundState.value())
    }

    private fun postAfterState(networkState: Listing.NetworkState) {
        network.postValue(networkState.value())
    }

    private fun initList(list: MutableList<BaseItemView>) =
        if (query.isEmpty()) listOf() else list

    private fun initPaging(refresh: Boolean) = if (refresh) null else PAGE_START + 1

    private fun initNetworkState() =
        if (query.isEmpty()) Listing.NetworkState.LOADED else Listing.NetworkState.LOADING

    private fun initBackgroundState() =
        if (initBackgroundState) Listing.BackgroundState.SHOW else Listing.BackgroundState.HIDE

    private fun refreshNetworkState(refresh: Boolean) =
        if (refresh) Listing.NetworkState.LOADED else Listing.NetworkState.LOADING

    private fun refreshBackgroundState(refresh: Boolean) =
        if (refresh) Listing.BackgroundState.SHOW else Listing.BackgroundState.HIDE

}
