package project.test.ru.testmvvm.feature.data.repository

import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner
import project.test.ru.testmvvm.feature.data.api.Api
import project.test.ru.testmvvm.feature.data.model.SearchResponseBean

@RunWith(MockitoJUnitRunner::class)
class SearchRepositoryImplTest {

    private lateinit var repositoryImpl: SearchRepositoryImpl
    @Mock
    lateinit var api: Api

    @Before
    fun setUp() {
        repositoryImpl = SearchRepositoryImpl(api)
    }

    @Test
    fun getSearchData() {
        val searchResponse = SearchResponseBean(10, emptyList())
        Mockito.`when`(api.search("test")).then { Single.just(searchResponse) }
        val resut = repositoryImpl.getSearchData("test").test()
        resut.assertNoErrors()
            .assertValue(SearchResponseBean(10, emptyList()))
    }
}