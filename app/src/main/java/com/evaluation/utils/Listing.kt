package com.evaluation.utils

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

/**
 * Data class that is necessary for a UI to show a listing and interact w/ the rest of the system
 */
data class Listing<T>(
        val pagedList: LiveData<PagedList<T>>,
        val networkState: LiveData<Boolean>,
        val backgroundState: LiveData<Boolean>,
) {
    enum class BackgroundState(private val state: Boolean) {
        SHOW(true),
        HIDE(false);

        fun value() = state
    }

    enum class NetworkState(private val state: Boolean) {
        LOADING(true),
        LOADED(false);

        fun value() = state
    }
}
