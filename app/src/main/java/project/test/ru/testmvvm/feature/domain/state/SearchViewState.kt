package project.test.ru.testmvvm.feature.domain.state

import project.test.ru.testmvvm.feature.domain.model.SearchResponse


sealed class SearchViewState {
    object LoadingState : SearchViewState()
    object SearchNotStartedYet : SearchViewState()
    object EmptyResult : SearchViewState()
    data class ShowDataState(val searchResponse: SearchResponse) : SearchViewState()
    data class ErrorState(val error: Throwable) : SearchViewState()
}