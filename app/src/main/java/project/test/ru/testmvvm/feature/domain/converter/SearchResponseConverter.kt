package project.test.ru.testmvvm.feature.domain.converter

import project.test.ru.testmvvm.feature.data.model.SearchResponseBean
import project.test.ru.testmvvm.feature.domain.model.SearchResponse
import project.test.ru.testmvvm.feature.domain.model.User


class SearchResponseConverter {

    companion object {
        fun toSearchResponse(searchResponseBean: SearchResponseBean): SearchResponse {
            val usersList = ArrayList<User>()
            searchResponseBean.items.forEach {
                usersList.add(User(it.id, it.login, it.avatar_url))
            }
            return SearchResponse().apply {
                total = searchResponseBean.total_count
                users = usersList
            }
        }
    }
}