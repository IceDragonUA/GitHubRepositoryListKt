package com.evaluation.mapper

import com.evaluation.favorites.model.RepositoryTableItem
import com.evaluation.search.model.Repository
import com.evaluation.search.model.User
import com.evaluation.utils.defIfNull
import javax.inject.Inject

/**
 * @author Vladyslav Havrylenko
 * @since 01.10.2020
 */
class Mapper @Inject constructor() {

    fun toTableItem(item: Repository): RepositoryTableItem {
        return item.let {
            RepositoryTableItem(
                name = it.name.defIfNull(),
                id = it.id.defIfNull(),
                avatar_url = it.owner.avatar_url.defIfNull(),
                html_url = it.html_url.defIfNull()
            )
        }
    }

    fun toViewItem(item: RepositoryTableItem): Repository {
        return item.let {
            Repository(
                name = it.name.defIfNull(),
                id = it.id.defIfNull(),
                owner = User(it.avatar_url.defIfNull()),
                html_url = it.html_url.defIfNull()
            )
        }
    }
}