package project.test.ru.testmvvm.feature.data.repository

import io.reactivex.Single
import project.test.ru.testmvvm.feature.data.api.Api
import project.test.ru.testmvvm.feature.data.model.SearchResponseBean
import javax.inject.Inject

interface SearchRepository {

    fun getSearchData(search: String): Single<SearchResponseBean>
}

class SearchRepositoryImpl @Inject constructor(private val api: Api) : SearchRepository {

    override fun getSearchData(search: String): Single<SearchResponseBean> = api.search(search)
}