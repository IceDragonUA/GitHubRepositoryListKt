package com.evaluation.adapter.viewholder.item

import com.evaluation.adapter.factory.TypesFactory
import com.evaluation.utils.NO_ITEM

/**
 * @author Vladyslav Havrylenko
 * @since 03.10.2020
 */
data class NoItemView(override var id: String = NO_ITEM, var title: String) : BaseItemView {

    override fun type(typesFactory: TypesFactory): Int = typesFactory.type(this)

}