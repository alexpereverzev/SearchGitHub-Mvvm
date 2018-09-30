package project.test.ru.testmvvm.core.di.module

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import project.test.ru.testmvvm.core.ViewModelFactory
import project.test.ru.testmvvm.core.di.annotation.FeatureScope
import project.test.ru.testmvvm.feature.view.MainActivity

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @FeatureScope
    @ContributesAndroidInjector(modules = [SearchModule::class])
    internal abstract fun contributeMainActivity(): MainActivity
}