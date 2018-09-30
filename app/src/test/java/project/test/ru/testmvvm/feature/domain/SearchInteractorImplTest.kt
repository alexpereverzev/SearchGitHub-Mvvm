package project.test.ru.testmvvm.feature.domain

import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner
import project.test.ru.testmvvm.feature.data.model.SearchResponseBean
import project.test.ru.testmvvm.feature.data.model.UserBean
import project.test.ru.testmvvm.feature.data.repository.SearchRepository
import project.test.ru.testmvvm.feature.domain.model.SearchResponse
import project.test.ru.testmvvm.feature.domain.model.User
import project.test.ru.testmvvm.feature.domain.state.SearchViewState

@RunWith(MockitoJUnitRunner::class)
class SearchInteractorImplTest {

    private lateinit var interactorImpl: SearchInteractorImpl
    @Mock
    lateinit var repositoryImpl: SearchRepository


    @Before
    fun setUp() {
        interactorImpl = SearchInteractorImpl(repositoryImpl)
    }

    @Test
    fun getSearchDataEmpty() {
        Mockito.`when`(repositoryImpl.getSearchData("test"))
            .then { Single.just(SearchResponseBean(10, emptyList())) }
        val resObservable = interactorImpl.getSearchData("test").test()
        resObservable.awaitTerminalEvent()
        resObservable.assertNoErrors()
            .assertValue(SearchViewState.EmptyResult)
    }

    @Test
    fun getSearchDataSuccess() {
        val data = ArrayList<UserBean>()
        data.add(UserBean(10, "test", "http:"))
        Mockito.`when`(repositoryImpl.getSearchData("test"))
            .then { Single.just(SearchResponseBean(10, data)) }

        val dataExpect = ArrayList<User>()
        dataExpect.add(User(10, "test", "http:"))
        val resObservable = interactorImpl.getSearchData("test").test()
        resObservable.awaitTerminalEvent()
        resObservable.assertNoErrors()
            .assertValue(SearchViewState.ShowDataState(SearchResponse(10, dataExpect)))

    }
}