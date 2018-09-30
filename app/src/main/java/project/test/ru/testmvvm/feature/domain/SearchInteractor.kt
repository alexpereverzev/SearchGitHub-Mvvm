package project.test.ru.testmvvm.feature.domain

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import project.test.ru.testmvvm.feature.data.repository.SearchRepository
import project.test.ru.testmvvm.feature.domain.converter.SearchResponseConverter
import project.test.ru.testmvvm.feature.domain.state.SearchViewState
import javax.inject.Inject


interface SearchInteractor {

    fun getSearchData(search: String): Single<SearchViewState>
}

class SearchInteractorImpl @Inject constructor(private var repository: SearchRepository) :
    SearchInteractor {

    override fun getSearchData(search: String): Single<SearchViewState> =
        if (search.isEmpty()) {
            Single.just(SearchViewState.SearchNotStartedYet)
        } else {
            repository.getSearchData(search)
                .subscribeOn(Schedulers.io())
                .map<SearchViewState> { result ->
                    if (result.items.isEmpty()) {
                        return@map SearchViewState.EmptyResult
                    } else {
                        return@map SearchViewState.ShowDataState(
                            SearchResponseConverter.toSearchResponse(
                                result
                            )
                        )
                    }
                }
        }

}