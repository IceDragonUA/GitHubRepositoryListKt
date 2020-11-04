package com.evaluation.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.evaluation.adapter.AdapterItemClickListener
import com.evaluation.adapter.FavoriteListAdapter
import com.evaluation.adapter.factory.TypesFactoryImpl
import com.evaluation.adapter.viewholder.item.BaseItemView

/**
 * @author Vladyslav Havrylenko
 * @since 03.10.2020
 */
class FavoriteRecyclerView : RecyclerView, AdapterItemClickListener<BaseItemView> {

    lateinit var listener: AdapterItemClickListener<BaseItemView>

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        layoutManager = LinearLayoutManager(context)
        adapter = FavoriteListAdapter(TypesFactoryImpl(), this)
        itemAnimator = DefaultItemAnimator()
    }

    override fun getAdapter(): FavoriteListAdapter =
        super.getAdapter() as FavoriteListAdapter

    override fun onClicked(item: BaseItemView) {
        listener.onClicked(item)
    }
}