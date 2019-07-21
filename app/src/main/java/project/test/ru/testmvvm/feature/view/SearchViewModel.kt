package project.test.ru.testmvvm.feature.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import project.test.ru.testmvvm.feature.domain.SearchInteractor
import project.test.ru.testmvvm.feature.domain.state.SearchViewState
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val interactor: SearchInteractor) : ViewModel() {

    val result = MutableLiveData<SearchViewState>()

    private val compositeDisposable = CompositeDisposable()

    fun search(search: Observable<String>) {
        compositeDisposable.add(search
            .doOnNext { result.value = SearchViewState.LoadingState }
            .switchMap {
                interactor.getSearchData(it).toObservable()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { state ->
                    result.value = state
                },
                { e ->
                    result.value = SearchViewState.ErrorState(e)
                }
            ))


    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}