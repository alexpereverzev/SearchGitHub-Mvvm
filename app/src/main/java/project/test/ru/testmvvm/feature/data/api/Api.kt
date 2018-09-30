package project.test.ru.testmvvm.feature.data.api

import io.reactivex.Single
import project.test.ru.testmvvm.feature.data.model.SearchResponseBean
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/search/users")
    fun search(@Query("q") search: String): Single<SearchResponseBean>
}