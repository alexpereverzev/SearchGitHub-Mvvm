package project.test.ru.testmvvm.core.di.module

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import project.test.ru.testmvvm.core.di.annotation.ViewModelKey
import project.test.ru.testmvvm.feature.data.repository.SearchRepository
import project.test.ru.testmvvm.feature.data.repository.SearchRepositoryImpl
import project.test.ru.testmvvm.feature.domain.SearchInteractor
import project.test.ru.testmvvm.feature.domain.SearchInteractorImpl
import project.test.ru.testmvvm.feature.view.SearchViewModel

@Module
internal abstract class SearchModule {

    @Binds
    abstract fun bindMainRepository(repositrory: SearchRepositoryImpl): SearchRepository

    @Binds
    internal abstract fun bindSearchInteractor(interactor: SearchInteractorImpl): SearchInteractor

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindMainViewModel(viewModel: SearchViewModel): ViewModel
}