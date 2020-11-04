package com.evaluation.adapter.viewholder.item

import com.evaluation.adapter.factory.TypesFactory
import com.evaluation.search.model.Repository

/**
 * @author Vladyslav Havrylenko
 * @since 03.10.2020
 */
data class CardItemView(override var id: String, var repository: Repository) : BaseItemView {

    override fun type(typesFactory: TypesFactory): Int = typesFactory.type(this)

}